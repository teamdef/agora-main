package def.agoramain.retro.problem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Problem {

    @Id @GeneratedValue
    private Long id;
    @NotNull
    private Long retroId;
    @NotNull
    private String content;
    @NotNull
    private Long createMemberId;
    @NotNull
    private Status status;

    @Builder
    public Problem(Long retroId, String content, Long createMemberId, Status status) {
        this.retroId = retroId;
        this.content = content;
        this.createMemberId = createMemberId;
        this.status = status;
    }

    public void updateStatus(Status status) {
        this.status = status;
    }

    public void updateContent(String content) {
        this.content = content;
    }

}