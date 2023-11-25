package def.agoramain.review;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/review")
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
}
