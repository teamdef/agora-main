package def.agoramain.review.problem.controller;

import def.agoramain.review.problem.service.ProblemService;
import def.agoramain.review.problem.dto.request.ProblemRequestDto;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/problem")
@RequiredArgsConstructor
public class ProblemController {
    private final ProblemService problemService;

    @ApiOperation(value="문제 저장", notes = "회고에 대한 문제를 생성합니다.")
    @PostMapping()
    public void saveProblem(@Valid @RequestBody ProblemRequestDto problemRequestDto)throws Exception{
        this.problemService.saveProblem(problemRequestDto);
    }
}
