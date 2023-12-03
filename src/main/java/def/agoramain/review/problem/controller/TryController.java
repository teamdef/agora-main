package def.agoramain.review.problem.controller;

import def.agoramain.review.problem.dto.request.TryReqDto;
import def.agoramain.review.problem.entity.Try;
import def.agoramain.review.problem.service.TryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Try", description = "문제 해결의 시도에 대한 API")
@RestController()
@RequestMapping("/try")
@RequiredArgsConstructor
public class TryController {

    private final TryService tryService;

    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "시도 생성 성공"),
            @ApiResponse(responseCode = "500", description = "시도 생성 실패")
    })
    @Operation(summary = "시도 생성", description = "문제에 대한 시도를 제기합니다.")
    @PostMapping()
    public void saveTry(@Valid @RequestBody TryReqDto tryRequestDto) throws Exception{
        Try tryEntity = tryRequestDto.toEntity();
        this.tryService.saveTry(tryEntity);
    }

    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "시도 내용 변경 성공"),
            @ApiResponse(responseCode = "500", description = "시도 내용 변경 실패")
    })
    @Operation(summary = "시도 내용 변경", description = "시도의 내용을 변경합니다.")
    @PatchMapping("/{tryId}/content")
    public void updateTryContent(
            @PathVariable("tryId") Long tryId,
            @RequestBody @NotNull String content) throws Exception{
        this.tryService.updateTryContent(tryId, content);
    }
}
