package def.agoramain.retro.problem.repo;

import def.agoramain.retro.problem.entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProblemRepo extends JpaRepository<Problem,Long> {

    @Override
    <S extends Problem> S save(S entity);

    List<Problem> findAllByRetroId(Long retroId);
}
