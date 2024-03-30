package def.agoramain.retro.keep.dto.request;

import def.agoramain.retro.keep.entity.Keep;
import def.agoramain.retro.problem.entity.Problem;
import def.agoramain.retro.problem.entity.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Schema(description = "")
@Getter
public class KeepReqDto {
    @Schema(description = "주간회고 ID", example = "1")
    @NotNull(message = "주간회고 ID를 입력하세요.")
    private Long retroId;

    @Schema(description = "지속할 점 내용", example = "진행 상황이 잘 공유되었음")
    @NotEmpty(message = "지속할 점 내용을 입력하세요.")
    private String content;

    @Schema(description = "지속할 점을 작성한 유저의 ID", example = "1")
    @NotNull(message = "지속할 점 작성한 유저의 ID를 입력하세요.")
    private Long authorId;

    public Keep toEntity(){
        return Keep.builder()
                .retroId(retroId)
                .authorId(authorId)
                .content(content)
                .build();
    }
}
