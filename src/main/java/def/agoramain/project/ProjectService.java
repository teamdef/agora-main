package def.agoramain.project;

import def.agoramain.common.MemberService;
import def.agoramain.project.entity.*;
import def.agoramain.retro.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProjectService {

    private final MemberService memberService;
    private final ProjectRepo projectRepo;
    private final ProjectMemberRepo projectMemberRepo;

    private final List<Long> DEFAULT_PROJECT_MEMBER_IDs = List.of(0L, 1L, 2L, 3L, 4L, 5L);

    public List<ProjectResDto> findProjectsByMember(String token) {

//        Long memberIdByToken = memberService.getMemberIdByToken(token);
        Long memberIdByToken = 0L;
        List<Project> projects = projectRepo.getProjectsByMemberId(memberIdByToken);


        List<ProjectResDto> projectResDtos = new ArrayList<>();

        for (Project project : projects) {

            List<ProjectMember> projectMembers = projectMemberRepo.getProjectMemberByProjectId(project.getId());
            List<Long> memberIds = projectMembers.stream().map(ProjectMember::getMemberId).collect(Collectors.toList());

            List<Member> members = memberService.requestMembers(memberIds);
            Long authorId = null;
            Member author = null;


            for (ProjectMember projectMember: projectMembers) {
                if (ProjectAuth.ADMIN.equals(projectMember.getAuth())) {
                    authorId = projectMember.getMemberId();
                }
            }

            for (Member member : members) {
                if (authorId != null && authorId.equals(member.getId())) {
                    author = member;
                }
            }

            members.remove(author);
            projectResDtos.add(new ProjectResDto(project, author, members));
        }

        return projectResDtos;
    }

    @Transactional
    public void createProject(String token, ProjectReqDto projectReqDto) {

//        Long memberIdByToken = memberService.getMemberIdByToken(token);
        Long memberIdByToken = 0L;

        Project project = Project.builder()
                .description(projectReqDto.projectDescription())
                .title(projectReqDto.projectTitle())
                .thumbnail(projectReqDto.projectThumbnail())
                .build();

        Project savedProject = projectRepo.save(project);

        List<ProjectMember> projectMembers = DEFAULT_PROJECT_MEMBER_IDs.stream()
                .filter(id -> !memberIdByToken.equals(id))
                .map(id -> new ProjectMember(savedProject.getId(), id, ProjectAuth.USER))
                .collect(Collectors.toList());

        projectMembers.add(new ProjectMember(project.getId(), memberIdByToken, ProjectAuth.ADMIN));

        projectMemberRepo.saveAll(projectMembers);
    }

    @Transactional
    public void deleteProject(String token, Long id) {

//        Long memberId = memberService.getMemberIdByToken(token);
        Long memberId = 0L;


        List<ProjectMember> projectMembers = projectMemberRepo.getProjectMemberByProjectId(id);

        for (ProjectMember projectMember : projectMembers) {
            if (ProjectAuth.ADMIN.equals(projectMember.getAuth()) && !projectMember.getMemberId().equals(memberId)) {
                throw new RuntimeException("프로젝트 작성자가 아닙니다.");
            }
        }

        projectRepo.deleteById(id);
        projectMemberRepo.deleteAllByProjectId(id);
    }


}
