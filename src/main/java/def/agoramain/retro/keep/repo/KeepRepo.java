package def.agoramain.retro.keep.repo;

import def.agoramain.retro.entity.Keep;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KeepRepo extends JpaRepository<Keep, Long> {
    List<Keep> findAllByRetroId(Long retroId);
}
