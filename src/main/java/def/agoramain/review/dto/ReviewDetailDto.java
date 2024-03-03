package def.agoramain.review.dto;

import def.agoramain.review.entity.Keep;
import def.agoramain.review.entity.Member;
import def.agoramain.review.entity.Review;
import def.agoramain.review.problem.dto.response.ProblemDetailResDto;
import def.agoramain.review.problem.entity.Problem;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ReviewDetailDto {

    private Long id;
    private String title;
    private Member creator;
    private LocalDateTime createTime;
    private Long projectId;
    private List<Member> members;
    private List<Keep> keeps;
    private List<ProblemDetailResDto> problems;

    public ReviewDetailDto(Review review, List<Member> members, List<ProblemDetailResDto> problems, List<Keep>keeps, Member creator) {
        this.id = review.getId();
        this.title = review.getTitle();
        this.createTime = review.getCreateTime();
        this.projectId = review.getProjectId();
        this.members = members;
        this.keeps = keeps;
        this.problems = problems;
        this.creator = creator;
    }
}
