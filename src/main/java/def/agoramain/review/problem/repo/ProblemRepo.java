package def.agoramain.review.problem.repo;

import def.agoramain.review.problem.entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemRepo extends JpaRepository<Problem,Long> {

    @Override
    <S extends Problem> S save(S entity);
}
