package def.agoramain.retro.keep.controller;

import def.agoramain.retro.keep.dto.request.KeepReqDto;
import def.agoramain.retro.keep.entity.Keep;
import def.agoramain.retro.keep.service.KeepService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name="Keep", description = "회고 속 지속할 점에 대한 API")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/keep")
@RequiredArgsConstructor
public class KeepController {
    private final KeepService keepService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "지속할 점 생성 성공"),
            @ApiResponse(responseCode = "500", description = "지속할 점 생성 실패")
    })
    @Operation(summary = "지속할 점 생성", description = "회고에 대한 지속할 점을 생성합니다.")
    @PostMapping
    public void saveKeep(@Valid @RequestBody KeepReqDto keepReqDto) throws Exception{
        Keep keep = keepReqDto.toEntity();
        this.keepService.saveKeep(keep);
    }

    @Operation(summary = "지속할 점 삭제", description = "지속할 점을 삭제합니다.")
    @DeleteMapping("/{keepId}")
    public void deleteKeep(
            @PathVariable("keepId") @NotNull Long keepId){
        this.keepService.deleteKeep(keepId);
    }
}
