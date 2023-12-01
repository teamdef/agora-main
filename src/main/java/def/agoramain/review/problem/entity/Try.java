package def.agoramain.review.problem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Try {

    @Id @GeneratedValue
    private Long id;
    @NotNull
    private Long problemId;
    @NotNull
    private Long createMemberId;
    @NotNull
    private String content;
}
