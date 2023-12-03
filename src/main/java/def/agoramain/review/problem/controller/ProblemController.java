package def.agoramain.review.problem.controller;

import def.agoramain.review.problem.service.ProblemService;
import def.agoramain.review.problem.dto.request.ProblemRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public void saveProblem(@Valid @RequestBody ProblemRequestDto problemRequestDto) {
        this.problemService.saveProblem(problemRequestDto);
    }

}
