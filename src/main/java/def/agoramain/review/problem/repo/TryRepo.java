package def.agoramain.review.problem.repo;

import def.agoramain.review.problem.entity.Try;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TryRepo extends JpaRepository<Try,Long> {
    List<Try> findAllByProblemId(Long problemId);
}
