package def.agoramain.retro;

import def.agoramain.retro.dto.request.ReqRetroDto;
import def.agoramain.retro.dto.response.RetroDetailDto;
import def.agoramain.retro.dto.response.RetroDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Retro", description = "회고에 대한 API")
@RestController
@RequestMapping("/retro")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class RetroController {

    private final RetroService retroService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회고 목록 조회 성공"),
            @ApiResponse(responseCode = "500", description = "회고 목록 조회 실패")
    })
    @Operation(summary = "회고 목록 조회", description = "프로젝트의 회고 목록을 조회합니다.")
    @GetMapping
    public ResponseEntity<List<RetroDto>> getRetros(
            @RequestParam int pageNo,
            @RequestParam int listSize,
            @RequestParam long projectId) {

        return ResponseEntity.ok(retroService.findRetros(projectId, PageRequest.of(pageNo - 1, listSize)));
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회고 상세 조회 성공"),
            @ApiResponse(responseCode = "500", description = "회고 상세 조회 실패", content = @Content)
    })
    @Operation(summary = "회고 상세 조회", description = "지속할 점, 문제와 연관된 시도를 포함한 상세 정보를 조회합니다.")
    @GetMapping("/{retroId}")
    public ResponseEntity<RetroDetailDto> getRetroDetail(
            @PathVariable Long retroId) throws Exception{

        return ResponseEntity.ok(retroService.findRetroDetail(retroId));
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회고 생성 성공"),
            @ApiResponse(responseCode = "500", description = "회고 생성 실패", content = @Content)
    })
    @Operation(summary = "회고 생성", description = "프로젝트의 회고를 생성합니다.")
    @PostMapping
    public void makeRetro(
            @RequestBody ReqRetroDto reqRetro) {

        retroService.makeRetro(reqRetro);
    }

    @Operation(summary = "회고 제목 변경", description = "회고 제목을 변경합니다")
    @PatchMapping("/{retroId}")
    public void changeRetroTitle(@PathVariable("retroId") Long retroId,
                                 @RequestBody ReqRetroDto reqRetro) {
        retroService.changeRetroTitle(retroId, reqRetro.getTitle());
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회고 삭제 성공"),
            @ApiResponse(responseCode = "500", description = "회고 삭제 실패", content = @Content)
    })
    @Operation(summary = "회고 삭제", description = "회고를 삭제합니다.")
    @DeleteMapping("/{retroId}")
    public void deleteRetro(
            @PathVariable("retroId") Long retroId
    ) {

        retroService.deleteRetro(retroId);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회고 참여자 삭제 성공"),
            @ApiResponse(responseCode = "500", description = "회고 참여자 삭제 실패", content = @Content)
    })
    @Operation(summary = "회고 참여자 삭제", description = "요청된 회고에 참여한 유저들을 삭제합니다.")
    @DeleteMapping("/{retroId}/members")
    public void deleteRetro(
            @PathVariable("retroId") Long retroId,
            @RequestParam("memberIds") List<Long> memberIds
    ) {

        retroService.deleteRetroMember(retroId, memberIds);
    }
}
