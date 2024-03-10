package def.agoramain.retro.repo;

import def.agoramain.retro.entity.RetroMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RetroMemberRepo extends JpaRepository<RetroMember, Long> {

    void deleteAllByRetroId(Long retroId);

    List<RetroMember> findAllByRetroId(Long retroId);
}
