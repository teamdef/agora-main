package def.agoramain.retro.keep.service;

import def.agoramain.retro.keep.entity.Keep;
import def.agoramain.retro.keep.repo.KeepRepo;
import def.agoramain.retro.repo.RetroRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KeepService {
    private final RetroRepo retroRepo;
    private final KeepRepo keepRepo;

    @Transactional
    public void saveKeep(Keep keepEntity) throws Exception{
        this.retroRepo
                .findById(keepEntity.getRetroId())
                .orElseThrow(()-> new ClassNotFoundException("해당하는 회고가 없습니다."));

        this.keepRepo.save(keepEntity);
    }
}
