package def.agoramain.review.dto;

import def.agoramain.review.entity.Review;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@NoArgsConstructor
public class ReqReviewDto {

    private Long projectId;
    private Long createMemberId;
    private List<Long> JoinMemberIds;
    private LocalDateTime createTime;
    private String title;
    private String content;

    public Review toEntity() {
        return Review.builder()
                .projectId(projectId)
                .title(title)
                .content(content)
                .createMemberId(createMemberId)
                .createTime(createTime)
                .build();
    }
}
