package def.agoramain.project.entity;

import def.agoramain.retro.entity.Member;
import lombok.Getter;

import java.util.List;

@Getter
public class ProjectResDto {

    private final Long projectId;
    private final String projectThumbnail;
    private final String projectTitle;
    private final String projectDescription;
    private final List<Member> members;
    private final Member author;

    public ProjectResDto(Project project, Member author, List<Member> members) {
        this.projectId = project.getId();
        this.projectThumbnail = project.getThumbnail();
        this.projectTitle = project.getTitle();
        this.projectDescription = project.getDescription();
        this.members = members;
        this.author = author;
    }
}
