package def.agoramain.review;

import def.agoramain.review.dto.ReqReviewDto;
import def.agoramain.review.dto.ReviewDetailDto;
import def.agoramain.review.dto.ReviewDto;
import def.agoramain.review.entity.Member;
import def.agoramain.review.entity.Review;
import def.agoramain.review.entity.ReviewAuth;
import def.agoramain.review.entity.ReviewMember;
import def.agoramain.review.repo.ReviewMemberRepo;
import def.agoramain.review.repo.ReviewRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static def.agoramain.common.URL.MEMBER_DETAIL_REQUEST_URL;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ReviewRepo reviewRepo;
    private final ReviewMemberRepo reviewMemberRepo;

    public List<ReviewDto> findReviews(Long projectId, Pageable page) {

        Page<Review> reviews = reviewRepo.findAllByProjectIdOrderByCreateTime(projectId, page);

        return reviews.getContent().stream().map(ReviewDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void makeReview(ReqReviewDto reviewDto) {

        Review review = reviewRepo.save(reviewDto.toEntity());
        mapReviewMembers(review.getId(), reviewDto);

    }

    @Transactional
    public void deleteReview(Long reviewId) {

        reviewRepo.deleteById(reviewId);
        reviewMemberRepo.deleteAllByReviewId(reviewId);
    }

    @Transactional
    public void deleteReviewMember(Long reviewId, List<Long> memberIds) {

        List<ReviewMember> reviewMembers = reviewMemberRepo.findAllByReviewId(reviewId);
        List<ReviewMember> filterMembers = reviewMembers
                .stream()
                .filter(reviewMember -> memberIds.contains(reviewMember.getMemberId()))
                .collect(Collectors.toList());

        reviewMemberRepo.deleteAll(filterMembers);
    }

    public ReviewDetailDto findReviewDetail(Long reviewId) throws Exception{

        Review review = reviewRepo.findById(reviewId).orElseThrow(ClassNotFoundException::new);
        List<Long> memberIds = reviewMemberRepo.findAllByReviewId(reviewId)
                .stream()
                .map(ReviewMember::getMemberId)
                .collect(Collectors.toList());

        List<Member> members = requestMembers(memberIds);

        return new ReviewDetailDto(review, members);

    }

    public List<Member> requestMembers(List<Long> memberIds) {

        List<Member> members = new ArrayList<>();

        for (Long memberId : memberIds) {

            ResponseEntity<Member> member = restTemplate
                    .getForEntity(MEMBER_DETAIL_REQUEST_URL.getUrl()+"/"+memberId.toString(), Member.class);

            if (member.getStatusCode().is2xxSuccessful()) members.add(member.getBody());
        }
        return members;
    }

    private void mapReviewMembers(Long reviewId, ReqReviewDto reviewDto) {

        reviewMemberRepo.save(ReviewMember.builder()
                .ReviewId(reviewId)
                .memberId(reviewDto.getCreateMemberId())
                .auth(ReviewAuth.ADMIN)
                .build());

        List<ReviewMember> reviewMembers = reviewDto.getJoinMemberIds().stream()
                .map(memberId -> ReviewMember.builder()
                        .ReviewId(reviewId)
                        .memberId(memberId)
                        .auth(ReviewAuth.USER).build())
                .collect(Collectors.toList());

        reviewMemberRepo.saveAll(reviewMembers);
    }
}
