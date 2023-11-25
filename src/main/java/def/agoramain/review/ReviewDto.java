package def.agoramain.review;

import def.agoramain.review.entity.Review;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReviewDto {

    private Long id;
    private String title;
    private LocalDateTime createTime;
    private Long projectId;

    public ReviewDto(Review review) {
        this.id = review.getId();
        this.title = review.getTitle();
        this.createTime = review.getCreateTime();
        this.projectId = review.getProjectId();
    }
}
