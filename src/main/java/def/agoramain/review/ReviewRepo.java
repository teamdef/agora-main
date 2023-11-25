package def.agoramain.review;

import def.agoramain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepo extends JpaRepository<Review, Long> {

    Page<Review> findAllByProjectIdOrderByCreateTime(Long projectId, Pageable pageable);

}
