package def.agoramain.review.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class ReviewMember {

    @Id
    @GeneratedValue
    private long id;
    @NotNull
    private Long ReviewId;
    @NotNull
    private Long memberId;
    @NotNull @Embedded @Enumerated(value = EnumType.STRING)
    private ReviewAuth auth;

}
