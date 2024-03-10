package def.agoramain.retro.dto;

import def.agoramain.retro.entity.Retro;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@NoArgsConstructor
public class ReqRetroDto {

    private Long projectId;
    private Long createMemberId;
    private List<Long> JoinMemberIds;
    private LocalDateTime createTime;
    private String title;
    private String content;

    public Retro toEntity() {
        return Retro.builder()
                .projectId(projectId)
                .title(title)
                .content(content)
                .createMemberId(createMemberId)
                .createTime(createTime)
                .build();
    }
}
