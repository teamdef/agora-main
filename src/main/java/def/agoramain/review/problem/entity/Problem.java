package def.agoramain.review.problem.entity;

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
    private Long reviewId;
    @NotNull
    private String content;
    @NotNull
    private Long createMemberId;
    @NotNull
    private Status status;

    @Builder
    public Problem(Long reviewId, String content, Long createMemberId, Status status) {
        this.reviewId = reviewId;
        this.content = content;
        this.createMemberId = createMemberId;
        this.status = status;
    }

    public void updateStatus(Status status) {
        this.status = status;
    }

}
