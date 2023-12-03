package def.agoramain.review.problem.service;

import def.agoramain.review.problem.entity.Try;
import def.agoramain.review.problem.repo.TryRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TryService {
    private final TryRepo tryRepo;

    @Transactional
    public void saveTry(Try tryEntity){
        this.tryRepo.save(tryEntity);
    }
}
