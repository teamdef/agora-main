package def.agoramain.retro.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Keep {

    @Id @GeneratedValue
    private Long id;
    @NotNull
    private Long retroId;
    @NotNull
    private String content;
    @NotNull
    private Long createMemberId;
}
