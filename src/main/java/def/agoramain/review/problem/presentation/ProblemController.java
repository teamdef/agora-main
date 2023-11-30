package def.agoramain.review.problem.presentation;

import def.agoramain.review.problem.application.ProblemService;
import def.agoramain.review.problem.dto.request.ProblemRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/problem")
@RequiredArgsConstructor
public class ProblemController {
    private final ProblemService problemService;

    @PostMapping()
    public void saveProblem(@Valid @RequestBody ProblemRequestDto problemRequestDto){
        this.problemService.saveProblem(problemRequestDto);
    }
}
