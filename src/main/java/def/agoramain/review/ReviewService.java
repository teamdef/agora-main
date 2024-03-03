package def.agoramain.review;

import def.agoramain.review.dto.ReqReviewDto;
import def.agoramain.review.dto.ReviewDetailDto;
import def.agoramain.review.dto.ReviewDto;
import def.agoramain.review.entity.*;
import def.agoramain.review.keep.repo.KeepRepo;
import def.agoramain.review.problem.dto.response.ProblemDetailResDto;
import def.agoramain.review.problem.entity.Problem;
import def.agoramain.review.problem.entity.Try;
import def.agoramain.review.problem.repo.ProblemRepo;
import def.agoramain.review.problem.repo.TryRepo;
import def.agoramain.review.repo.ReviewMemberRepo;
import def.agoramain.review.repo.ReviewRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static def.agoramain.common.URL.MEMBER_DETAIL_REQUEST_URL;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {

    private final KeepRepo keepRepo;
    private final ProblemRepo problemRepo;
    private final TryRepo tryRepo;
    private final RestTemplate restTemplate = new RestTemplate();
    private final ReviewRepo reviewRepo;
    private final ReviewMemberRepo reviewMemberRepo;

    // TODO: 하.. 일단 되게만 하고 쿼리하는 부분 다 바꿔야 함
    public List<ReviewDto> findReviews(Long projectId, Pageable page) {

        Page<Review> reviews = reviewRepo.findAllByProjectIdOrderByCreateTime(projectId, page);

        // member 들의 unique Set 을 구해서 한번에 요청 하기 위한 용도임
        Set<Long> uniqueMemberIds = new HashSet<>();

        List<List<Long>> membersInReviews = reviews.stream().map(review -> {
            List<Long> memberIds = reviewMemberRepo.findAllByReviewId(review.getId())
                    .stream().map(ReviewMember::getMemberId).toList();
            uniqueMemberIds.addAll(memberIds);
            return memberIds;
        }).toList();

        // member 정보 조회
        List<Member> members = requestMembers(uniqueMemberIds.stream().toList());
        Map<Long, Member> memberMap = members
                .stream()
                .collect(Collectors.toMap(Member::getId, Function.identity()));

        Iterator<Review> reviewsIt = reviews.getContent().stream().iterator();
        Iterator<List<Long>> memberIdsIt = membersInReviews.iterator();
        List<ReviewDto> reviewDtos = new ArrayList<>();

        while(reviewsIt.hasNext() && memberIdsIt.hasNext()){
            List<Member> engagedMembers = memberIdsIt
                    .next()
                    .stream()
                    .map(memberMap::get)
                    .toList();
            reviewDtos.add(new ReviewDto(reviewsIt.next(), engagedMembers));
        }

        return reviewDtos;
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

        // TODO: review_member 테이블 활용 필요
        Long creatorId = review.getCreateMemberId();
        memberIds.add(creatorId);

        List<Member> members = requestMembers(memberIds);
        List<Keep> keeps = keepRepo.findAllByReviewId(reviewId);
        List<Problem> problems = problemRepo.findAllByReviewId(reviewId);

        // TODO: 그.. join 사용하는 방법 적용해서 쿼리 날려야 함.
        List<ProblemDetailResDto> problemResults = problems
                .stream()
                .map(problem -> {
                    List<Try> tries = tryRepo.findAllByProblemId(problem.getId());
                    return new ProblemDetailResDto(problem, tries);
                }).toList();

        Member creator = members.stream()
                .filter(member-> member.getId().equals(creatorId))
                .toList()
                .get(0);

        return new ReviewDetailDto(review, members, problemResults, keeps, creator);

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
