package def.agoramain.review.problem.dto.response;

import def.agoramain.review.problem.entity.Problem;
import def.agoramain.review.problem.entity.Status;
import def.agoramain.review.problem.entity.Try;

import java.util.List;

public class ProblemDetailResponseDto {
    private Long id;
    private Long reviewId;
    private String content;
    private Long createMemberId;
    private Status status;
    private List<Try> tries;

    public ProblemDetailResponseDto(Problem problem, List<Try> tries) {
        this.id = problem.getId();
        this.reviewId = problem.getReviewId();
        this.content = problem.getContent();
        this.createMemberId = problem.getCreateMemberId();
        this.status = problem.getStatus();
        this.tries = tries;
    }
}
