package def.agoramain.review.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
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
    private Long reviewId;
    @NotNull
    private Long memberId;
    @NotNull @Enumerated(value = EnumType.STRING)
    private ReviewAuth auth;

    @Builder
    public ReviewMember(Long ReviewId, Long memberId, ReviewAuth auth) {
        this.reviewId = ReviewId;
        this.memberId = memberId;
        this.auth = auth;
    }

}
