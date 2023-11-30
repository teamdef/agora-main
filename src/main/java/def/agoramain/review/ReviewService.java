package def.agoramain.review;

import def.agoramain.review.dto.ReqReviewDto;
import def.agoramain.review.dto.ReviewDto;
import def.agoramain.review.entity.Review;
import def.agoramain.review.entity.ReviewAuth;
import def.agoramain.review.entity.ReviewMember;
import def.agoramain.review.repo.ReviewMemberRepo;
import def.agoramain.review.repo.ReviewRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {

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
