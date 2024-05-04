package def.agoramain.retro.problem.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Schema(description = "문제 내용 수정 요청 모델")
@Getter
public class ProblemContentReqDto {
    @Schema(description = "변경할 내용", example = "수정하고자 하는 내용")
    @NotNull(message = "변경할 내용을 입력하세요.")
    private String content;
}
