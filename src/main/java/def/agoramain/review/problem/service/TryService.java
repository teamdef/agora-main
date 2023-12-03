package def.agoramain.review.problem.service;

import def.agoramain.review.entity.Member;
import def.agoramain.review.entity.ReviewMember;
import def.agoramain.review.problem.entity.Problem;
import def.agoramain.review.problem.entity.Try;
import def.agoramain.review.problem.repo.ProblemRepo;
import def.agoramain.review.problem.repo.TryRepo;
import def.agoramain.review.repo.ReviewMemberRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TryService {
    private final TryRepo tryRepo;
    private final ProblemRepo problemRepo;

    @Transactional
    public void saveTry(Try tryEntity){
        Optional<Problem> problem = this.problemRepo.findById(tryEntity.getProblemId());
        if(problem.isEmpty()){
            // TODO: exception handler 추가
            throw new NotFoundException("problemId가 유효하지 않습니다.");
        }

        this.tryRepo.save(tryEntity);
    }
}
