package def.agoramain.review.problem.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ProblemRequestDto {
    @NotNull(message = "주간회고 ID를 입력하세요.")
    private Long reviewId;

    @NotEmpty(message = "문제 내용을 입력하세요.")
    private String content;

    @NotNull(message = "문제를 제기한 유저의 ID를 입력하세요.")
    private Long createdMemberId;
}
