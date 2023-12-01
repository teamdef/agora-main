package def.agoramain.review.repo;

import def.agoramain.review.entity.ReviewMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewMemberRepo extends JpaRepository<ReviewMember, Long> {

    void deleteAllByReviewId(Long reviewId);

    List<ReviewMember> findAllByReviewId(Long reviewId);
}
