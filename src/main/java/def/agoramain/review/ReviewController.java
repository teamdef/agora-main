package def.agoramain.review;

import def.agoramain.review.dto.ReqReviewDto;
import def.agoramain.review.dto.ReviewDetailDto;
import def.agoramain.review.dto.ReviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<ReviewDto>> getReviews(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam long projectId) {

        return ResponseEntity.ok(reviewService.findReviews(projectId, PageRequest.of(page - 1, size)));
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewDetailDto> getReviewDetail(
            @PathVariable Long reviewId) throws Exception{

        return ResponseEntity.ok(reviewService.findReviewDetail(reviewId));
    }

    @PostMapping
    public void makeReview(
            @RequestBody ReqReviewDto reqReview) {

        reviewService.makeReview(reqReview);
    }

    @DeleteMapping("/{reviewId}")
    public void deleteReview(
            @PathVariable("reviewId") Long reviewId
    ) {

        reviewService.deleteReview(reviewId);
    }

    @DeleteMapping("/{reviewId}/members")
    public void deleteReview(
            @PathVariable("reviewId") Long reviewId,
            @RequestParam("memberIds") List<Long> memberIds
    ) {

        reviewService.deleteReviewMember(reviewId, memberIds);
    }
}
