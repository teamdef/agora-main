package def.agoramain.review.problem.controller;

import def.agoramain.review.problem.dto.request.TryRequestDto;
import def.agoramain.review.problem.entity.Try;
import def.agoramain.review.problem.service.TryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Try", description = "문제 해결의 시도에 대한 API")
@RestController()
@RequestMapping("/try")
@RequiredArgsConstructor
public class TryController {

    private final TryService tryService;

    @PostMapping()
    public void saveTry(@Valid @RequestBody TryRequestDto tryRequestDto) {
        Try tryEntity = tryRequestDto.toEntity();
        this.tryService.saveTry(tryEntity);
    }
}
