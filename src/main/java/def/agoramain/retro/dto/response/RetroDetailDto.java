package def.agoramain.retro.dto.response;

import def.agoramain.retro.keep.entity.Keep;
import def.agoramain.retro.entity.Member;
import def.agoramain.retro.entity.Retro;
import def.agoramain.retro.problem.dto.response.ProblemDetailResDto;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class RetroDetailDto {

    private Long id;
    private String title;
    private Member creator;
    private LocalDateTime createTime;
    private Long projectId;
    private List<Member> members;
    private List<Keep> keeps;
    private List<ProblemDetailResDto> problems;

    public RetroDetailDto(Retro retro, List<Member> members, List<ProblemDetailResDto> problems, List<Keep>keeps, Member creator) {
        this.id = retro.getId();
        this.title = retro.getTitle();
        this.createTime = retro.getCreateTime();
        this.projectId = retro.getProjectId();
        this.members = members;
        this.keeps = keeps;
        this.problems = problems;
        this.creator = creator;
    }
}
