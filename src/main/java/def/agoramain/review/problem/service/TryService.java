package def.agoramain.review.problem.service;

import def.agoramain.review.problem.entity.Try;
import def.agoramain.review.problem.repo.ProblemRepo;
import def.agoramain.review.problem.repo.TryRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TryService {
    private final TryRepo tryRepo;
    private final ProblemRepo problemRepo;

    @Transactional
    public void saveTry(Try tryEntity) throws Exception{
        this.problemRepo
                .findById(tryEntity.getProblemId())
                .orElseThrow(()-> new ClassNotFoundException("해당하는 문제가 없습니다."));

        this.tryRepo.save(tryEntity);
    }
}
