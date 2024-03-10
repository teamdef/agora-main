package def.agoramain.retro.problem.repo;

import def.agoramain.retro.problem.entity.Try;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TryRepo extends JpaRepository<Try,Long> {
    List<Try> findAllByProblemId(Long problemId);
    void deleteAllByProblemId(Long problemId);
}
