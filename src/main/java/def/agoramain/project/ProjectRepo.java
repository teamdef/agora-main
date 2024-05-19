package def.agoramain.project;

import def.agoramain.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepo extends JpaRepository<Project, Long> {

    @Query(
            value = "SELECT p " +
                    "FROM Project p " +
                    "LEFT JOIN ProjectMember pm on pm.projectId = p.id " +
                    "WHERE pm.memberId = :memberId "
    )
    List<Project> getProjectsByMemberId(@Param("memberId") Long memberId);
}
