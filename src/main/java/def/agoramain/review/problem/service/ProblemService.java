package def.agoramain.review.problem.service;

import def.agoramain.review.entity.Review;
import def.agoramain.review.problem.dto.request.ProblemRequestDto;
import def.agoramain.review.problem.entity.Problem;
import def.agoramain.review.problem.entity.Status;
import def.agoramain.review.problem.repo.ProblemRepo;
import def.agoramain.review.repo.ReviewRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProblemService {
    private final ProblemRepo problemRepo;
    private final ReviewRepo reviewRepo;

    @Transactional
    public void saveProblem(ProblemRequestDto problemRequestDto) throws Exception{
        Optional<Review> review = this.reviewRepo.findById(problemRequestDto.getReviewId());

        if(review.isEmpty()){
            // TODO: exception handler 추가
            throw new Exception("reviewId가 유효하지 않습니다.");
        }

        Problem problem = Problem.builder()
                .content(problemRequestDto.getContent())
                .createMemberId(problemRequestDto.getCreatedMemberId())
                .reviewId(problemRequestDto.getReviewId())
                .status(Status.Start)
                .build();

        this.problemRepo.save(problem);
    }
}
