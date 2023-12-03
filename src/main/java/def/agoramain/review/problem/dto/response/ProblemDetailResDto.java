package def.agoramain.review.problem.dto.response;

import def.agoramain.review.problem.entity.Problem;
import def.agoramain.review.problem.entity.Status;
import def.agoramain.review.problem.entity.Try;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "문제 상세 조회 응답 모델")
public class ProblemDetailResDto {
    @Schema(description = "문제 ID", example = "1")
    private Long id;
    @Schema(description = "주간회고 ID", example = "1")
    private Long reviewId;
    @Schema(description = "문제 내용", example = "스프린트 진행률이 지난 번보다 30% 떨어짐")
    private String content;
    @Schema(description = "문제를 제기한 유저의 ID", example = "1")
    private Long createMemberId;
    @Schema(description = "문제 상태", example = "Start")
    private Status status;
    @Schema(description = "시도 리스트")
    private List<Try> tries;

    public ProblemDetailResDto(Problem problem, List<Try> tries) {
        this.id = problem.getId();
        this.reviewId = problem.getReviewId();
        this.content = problem.getContent();
        this.createMemberId = problem.getCreateMemberId();
        this.status = problem.getStatus();
        this.tries = tries;
    }
}
