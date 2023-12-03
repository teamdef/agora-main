package def.agoramain.review.problem.dto.request;

import def.agoramain.review.problem.entity.Problem;
import def.agoramain.review.problem.entity.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Schema(description = "문제 생성 요청 모델")
@Getter
public class ProblemRequestDto {
    @Schema(description = "주간회고 ID", example = "1")
    @NotNull(message = "주간회고 ID를 입력하세요.")
    private Long reviewId;

    @Schema(description = "문제 내용", example = "스프린트 진행률이 지난 번보다 30% 떨어짐")
    @NotEmpty(message = "문제 내용을 입력하세요.")
    private String content;

    @Schema(description = "문제를 제기한 유저의 ID", example = "1")
    @NotNull(message = "문제를 제기한 유저의 ID를 입력하세요.")
    private Long createdMemberId;

    public Problem toEntity(){
        return Problem.builder()
                .reviewId(reviewId)
                .createMemberId(createdMemberId)
                .content(content)
                .status(Status.Start)
                .build();
    }
}
