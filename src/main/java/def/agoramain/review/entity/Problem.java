package def.agoramain.review.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Problem {

    @Id @GeneratedValue
    private Long id;
    @NotNull
    private Long reviewId;
    @NotNull
    private String content;
    @NotNull
    private Long createMemberId;
    @NotNull
    private Status status;

}
