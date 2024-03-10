package def.agoramain.retro.problem.controller;

import def.agoramain.retro.problem.dto.response.ProblemDetailResDto;
import def.agoramain.retro.problem.entity.Problem;
import def.agoramain.retro.problem.entity.Status;
import def.agoramain.retro.problem.service.ProblemService;
import def.agoramain.retro.problem.dto.request.ProblemReqDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Problem", description = "회고 속 문제에 대한 API")
@RestController()
@RequestMapping("/problem")
@RequiredArgsConstructor
public class ProblemController {
    private final ProblemService problemService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "문제 생성 성공"),
            @ApiResponse(responseCode = "500", description = "문제 생성 실패")
    })
    @Operation(summary = "문제 생성", description = "회고에 대한 문제를 생성합니다.")
    @PostMapping()
    public void saveProblem(@Valid @RequestBody ProblemReqDto problemReqDto) throws Exception {
        Problem problem = problemReqDto.toEntity();
        this.problemService.saveProblem(problem);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "문제 상세 조회 성공"),
            @ApiResponse(responseCode = "500", description = "문제 상세 조회 실패", content = @Content)
    })
    @Operation(summary = "문제 상세 조회", description = "문제와 연관된 시도를 포함한 상세 정보를 조회합니다.")
    @GetMapping("/{problemId}")
    public ResponseEntity<ProblemDetailResDto> getProblemDetail(
            @PathVariable("problemId") @NotNull Long problemId) throws Exception{
        return ResponseEntity.ok(this.problemService.getProblemDetail(problemId));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "문제 상태 변경 성공"),
            @ApiResponse(responseCode = "500", description = "문제 상태 변경 실패")
    })
    @Operation(summary = "문제 상태 변경", description = "문제의 상태를 변경합니다.")
    @PatchMapping("/{problemId}/status/{status}")
    public void modifyProblemStatus(
            @PathVariable("problemId") @NotNull Long problemId,
            @PathVariable("status") @NotNull @Parameter(description = "변경될 상태") Status status) throws Exception{
        this.problemService.modifyProblemStatus(problemId, status);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "문제 내용 변경 성공"),
            @ApiResponse(responseCode = "500", description = "문제 내용 변경 실패")
    })
    @Operation(summary = "문제 내용 변경", description = "문제의 내용을 변경합니다.")
    @PatchMapping("/{problemId}/content")
    public void modifyProblemContent(
            @PathVariable("problemId") @NotNull Long problemId,
            @RequestBody @NotNull String content) throws Exception{
        this.problemService.modifyProblemContent(problemId, content);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "문제 삭제 성공"),
            @ApiResponse(responseCode = "500", description = "문제 삭제 실패")
    })
    @Operation(summary = "문제 삭제", description = "문제를 삭제합니다. 연관된 시도도 모두 삭제됩니다.")
    @DeleteMapping("/{problemId}")
    public void deleteProblem(
            @PathVariable("problemId") @NotNull Long problemId){
        this.problemService.deleteProblem(problemId);
    }
}
