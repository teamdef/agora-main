package def.agoramain.retro.repo;

import def.agoramain.retro.entity.Retro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RetroRepo extends JpaRepository<Retro, Long> {

    Page<Retro> findAllByProjectIdOrderByCreateTime(Long projectId, Pageable pageable);

}
