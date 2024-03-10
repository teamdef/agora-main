package def.agoramain.retro.problem.dto.request;

import def.agoramain.retro.problem.entity.Try;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Schema(description = "시도 생성 요청 모델")
@Getter
public class TryReqDto {
    @Schema(description = "문제 ID", example = "1")
    @NotNull(message = "문제 ID를 입력하세요.")
    private Long problemId;

    @Schema(description = "시도 내용", example = "3일내 달성할 수 있는 목표 설정 및 추적")
    @NotEmpty(message = "시도 내용을 입력하세요.")
    private String content;

    @Schema(description = "시도를 제기한 유저의 ID", example = "1")
    @NotNull(message = "시도를 제기한 유저의 ID를 입력하세요.")
    private Long createdMemberId;

    public Try toEntity(){
        return Try.builder()
                .problemId(problemId)
                .createMemberId(createdMemberId)
                .content(content)
                .build();
    }
}
