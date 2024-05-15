package def.agoramain.project;

import def.agoramain.project.entity.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectMemberRepo extends JpaRepository<ProjectMember, Long> {

    List<ProjectMember> getProjectMemberByProjectId(Long Id);

    void deleteAllByProjectId(Long id);
}
