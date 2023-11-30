package def.agoramain.review;

import def.agoramain.review.dto.ReqReviewDto;
import def.agoramain.review.dto.ReviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
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

    @PostMapping
    public void makeReview(
            @RequestBody ReqReviewDto reqReview) {

        reviewService.makeReview(reqReview);
    }
}
