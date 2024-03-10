package def.agoramain.retro.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class RetroMember {

    @Id
    @GeneratedValue
    private long id;
    @NotNull
    private Long retroId;
    @NotNull
    private Long memberId;
    @NotNull @Enumerated(value = EnumType.STRING)
    private RetroAuth auth;

    @Builder
    public RetroMember(Long retroId, Long memberId, RetroAuth auth) {
        this.retroId = retroId;
        this.memberId = memberId;
        this.auth = auth;
    }

}
