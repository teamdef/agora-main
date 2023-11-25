package def.agoramain.project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class ProjectMember {

    @Id
    @GeneratedValue
    private long id;
    @NotNull
    private Long projectId;
    @NotNull
    private Long memberId;
    @NotNull @Embedded @Enumerated(value = EnumType.STRING)
    private ProjectAuth auth;
}
