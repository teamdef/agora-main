package def.agoramain.review.dto;

import def.agoramain.review.entity.Member;
import def.agoramain.review.entity.Review;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ReviewDetailDto {

    private Long id;
    private String title;
    private LocalDateTime createTime;
    private Long projectId;
    private List<Member> members;

    public ReviewDetailDto(Review review, List<Member> members) {
        this.id = review.getId();
        this.title = review.getTitle();
        this.createTime = review.getCreateTime();
        this.projectId = review.getProjectId();
        this.members = members;
    }
}
