package def.agoramain.retro.dto;

import def.agoramain.retro.entity.Member;
import def.agoramain.retro.entity.Retro;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class RetroDto {

    private Long id;
    private String title;
    private LocalDateTime createTime;
    private Long projectId;
    private List<Member> members;

    public RetroDto(Retro retro, List<Member> members) {
        this.id = retro.getId();
        this.title = retro.getTitle();
        this.createTime = retro.getCreateTime();
        this.projectId = retro.getProjectId();
        this.members = members;
    }
}
