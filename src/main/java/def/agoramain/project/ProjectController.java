package def.agoramain.project;

import def.agoramain.project.entity.ProjectReqDto;
import def.agoramain.project.entity.ProjectResDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Project", description = "프로젝트에 대한 API")
@RestController
@RequestMapping("/project")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "프로젝트 목록 조회 성공"),
            @ApiResponse(responseCode = "500", description = "프로젝트 목록 조회 실패")
    })
    @Operation(summary = "프로젝트 목록 조회", description = "프로젝트 목록을 조회합니다.")
    @GetMapping
    public ResponseEntity<List<ProjectResDto>> getProjects(@RequestHeader("Authorization") String token) {

        return ResponseEntity.ok(projectService.findProjectsByMember(token));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "프로젝트 생성 성공"),
            @ApiResponse(responseCode = "500", description = "프로젝트 생성 실패")
    })
    @Operation(summary = "프로섹트 생성", description = "프로젝트를 생성합니다.")
    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createProject(
            @RequestHeader("Authorization") String token,
            @RequestBody ProjectReqDto projectReqDto) {

        projectService.createProject(token, projectReqDto);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "프로젝트 삭제 성공"),
            @ApiResponse(responseCode = "500", description = "프로젝트 삭제 실패, 삭체할 프로젝트 권한이 없는경우")
    })
    @Operation(summary = "프로젝트 삭제", description = "프로젝트를 삭제 합니다.")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProject(@RequestHeader("Authorization") String token,
                              @PathVariable("id") Long id) {

        projectService.deleteProject(token, id);
    }
}
