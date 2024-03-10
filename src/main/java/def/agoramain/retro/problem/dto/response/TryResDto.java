package def.agoramain.retro.problem.dto.response;

import def.agoramain.retro.problem.entity.Try;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "시도 응답 모델")
public class TryResDto {
    @Schema(description = "시도 ID", example = "1")
    private Long id;
    @Schema(description = "문제 ID", example = "1")
    private Long problemId;
    @Schema(description = "시도를 제기한 유저의 ID", example = "1")
    private Long createMemberId;
    @Schema(description = "시도 내용", example = "3일내 달성할 수 있는 목표 설정 및 추적")
    private String content;

    public TryResDto(Try tryEntity) {
        this.id = tryEntity.getId();
        this.problemId = tryEntity.getProblemId();
        this.createMemberId = tryEntity.getCreateMemberId();
        this.content = tryEntity.getContent();
    }
}
