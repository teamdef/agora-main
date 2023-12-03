package def.agoramain.review.problem.dto.response;

import def.agoramain.review.problem.entity.Try;

public class TryResponseDto {
    private Long id;
    private Long problemId;
    private Long createMemberId;
    private String content;

    public TryResponseDto(Try tryEntity) {
        this.id = tryEntity.getId();
        this.problemId = tryEntity.getProblemId();
        this.createMemberId = tryEntity.getCreateMemberId();
        this.content = tryEntity.getContent();
    }
}
