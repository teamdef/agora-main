package def.agoramain.review;

import def.agoramain.review.entity.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepo reviewRepo;

    public List<ReviewDto> findReviews(long projectId, Pageable page) {

        Page<Review> reviews = reviewRepo.findAllByProjectIdOrderByCreateTime(projectId, page);

        return reviews.getContent().stream().map(ReviewDto::new).collect(Collectors.toList());
    }
}
