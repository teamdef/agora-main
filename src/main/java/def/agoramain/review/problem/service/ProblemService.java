package def.agoramain.review.problem.service;

import def.agoramain.review.entity.Review;
import def.agoramain.review.entity.ReviewMember;
import def.agoramain.review.problem.dto.request.ProblemRequestDto;
import def.agoramain.review.problem.dto.response.ProblemDetailResponseDto;
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
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProblemService {
    private final TryRepo tryRepo;
    private final ProblemRepo problemRepo;
    private final ReviewRepo reviewRepo;
    private final ReviewMemberRepo reviewMemberRepo;

    @Transactional
    public void saveProblem(Problem problem){
        Optional<Review> review = this.reviewRepo.findById(problem.getReviewId());
        if(review.isEmpty()){
            // TODO: exception handler 추가
            throw new NotFoundException("reviewId가 유효하지 않습니다.");
        }

        Optional<ReviewMember> reviewMember = this.reviewMemberRepo.findById(problem.getCreateMemberId());
        if(reviewMember.isEmpty()){
            // TODO: exception handler 추가
            throw new NotFoundException("reviewMemberId가 유효하지 않습니다.");
        }

        this.problemRepo.save(problem);
    }

    @Transactional
    public ProblemDetailResponseDto getProblemDetail(Long problemId) throws Exception{
        Problem problem = this.problemRepo.findById(problemId).orElseThrow(ClassNotFoundException::new);
        List<Try> tries = this.tryRepo.findAllByProblemId(problemId);

        return new ProblemDetailResponseDto(problem, tries);
    }
}
