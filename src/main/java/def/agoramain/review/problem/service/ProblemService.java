package def.agoramain.review.problem.service;

import def.agoramain.review.problem.dto.response.ProblemDetailResDto;
import def.agoramain.review.problem.entity.Problem;
import def.agoramain.review.problem.entity.Status;
import def.agoramain.review.problem.entity.Try;
import def.agoramain.review.problem.repo.ProblemRepo;
import def.agoramain.review.problem.repo.TryRepo;
import def.agoramain.review.repo.ReviewMemberRepo;
import def.agoramain.review.repo.ReviewRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProblemService {
    private final TryRepo tryRepo;
    private final ProblemRepo problemRepo;
    private final ReviewRepo reviewRepo;
    private final ReviewMemberRepo reviewMemberRepo;

    @Transactional
    public void saveProblem(Problem problem) throws Exception{
        this.reviewRepo
                .findById(problem.getReviewId())
                .orElseThrow(()-> new ClassNotFoundException("해당하는 회고가 없습니다."));
        this.reviewMemberRepo
                .findById(problem.getCreateMemberId())
                .orElseThrow(()-> new ClassNotFoundException("해당하는 유저가 회고에 없습니다."));

        this.problemRepo.save(problem);
    }

    @Transactional
    public ProblemDetailResDto getProblemDetail(Long problemId) throws Exception{
        Problem problem = this.problemRepo
                .findById(problemId)
                .orElseThrow(()-> new ClassNotFoundException("해당하는 문제가 없습니다."));
        List<Try> tries = this.tryRepo.findAllByProblemId(problemId);

        return new ProblemDetailResDto(problem, tries);
    }

    @Transactional
    public void modifyProblemStatus(Long problemId, Status status) throws Exception{
        Problem problem = this.problemRepo
                .findById(problemId)
                .orElseThrow(()-> new ClassNotFoundException("해당하는 문제가 없습니다."));

        problem.updateStatus(status);
        this.problemRepo.save(problem);
    }

    @Transactional
    public void modifyProblemContent(Long problemId, String content) throws Exception{
        Problem problem = this.problemRepo
                .findById(problemId)
                .orElseThrow(()-> new ClassNotFoundException("해당하는 문제가 없습니다."));

        problem.updateContent(content);
        this.problemRepo.save(problem);
    }

    @Transactional
    public void deleteProblem(Long problemId) {
        this.problemRepo.deleteById(problemId);
        this.tryRepo.deleteAllByProblemId(problemId);
    }
}
