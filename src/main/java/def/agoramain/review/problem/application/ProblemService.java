package def.agoramain.review.problem.application;

import def.agoramain.review.problem.dto.request.ProblemRequestDto;
import def.agoramain.review.problem.entity.Problem;
import def.agoramain.review.problem.repo.ProblemRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProblemService {
    private final ProblemRepo problemRepo;

    public void saveProblem(ProblemRequestDto problemRequestDto){

        Problem problem = Problem.builder()
                .content(problemRequestDto.getContent())
                .createMemberId(problemRequestDto.getCreatedMemberId())
                .reviewId(problemRequestDto.getReviewId())
                .build();

        this.problemRepo.save(problem);
    }
}
