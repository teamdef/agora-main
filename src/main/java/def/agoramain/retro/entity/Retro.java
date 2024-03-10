package def.agoramain.retro.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Retro {

    @Id @GeneratedValue
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private String content;
    // TODO: retro_member 테이블 활용 필요
    @NotNull
    private Long createMemberId;
    @NotNull
    private LocalDateTime createTime;
    @NotNull
    private Long projectId;

    @Builder
    public Retro(String title, String content, Long createMemberId, Long projectId, LocalDateTime createTime) {
        this.title = title;
        this.content = content;
        this.createTime = createTime;
        this.projectId = projectId;
        this.createMemberId = createMemberId;
    }

}
