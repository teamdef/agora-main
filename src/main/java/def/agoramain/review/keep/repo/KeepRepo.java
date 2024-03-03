package def.agoramain.review.keep.repo;

import def.agoramain.review.entity.Keep;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KeepRepo extends JpaRepository<Keep, Long> {
    List<Keep> findAllByReviewId(Long reviewId);
}
