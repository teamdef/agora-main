package def.agoramain.retro;

import def.agoramain.retro.dto.ReqRetroDto;
import def.agoramain.retro.dto.RetroDetailDto;
import def.agoramain.retro.dto.RetroDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/retro")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class RetroController {

    private final RetroService retroService;

    @GetMapping
    public ResponseEntity<List<RetroDto>> getRetros(
            @RequestParam int pageNo,
            @RequestParam int listSize,
            @RequestParam long projectId) {

        return ResponseEntity.ok(retroService.findRetros(projectId, PageRequest.of(pageNo - 1, listSize)));
    }

    @GetMapping("/{retroId}")
    public ResponseEntity<RetroDetailDto> getRetroDetail(
            @PathVariable Long retroId) throws Exception{

        return ResponseEntity.ok(retroService.findRetroDetail(retroId));
    }

    @PostMapping
    public void makeRetro(
            @RequestBody ReqRetroDto reqRetro) {

        retroService.makeRetro(reqRetro);
    }

    @DeleteMapping("/{retroId}")
    public void deleteRetro(
            @PathVariable("retroId") Long retroId
    ) {

        retroService.deleteRetro(retroId);
    }

    @DeleteMapping("/{retroId}/members")
    public void deleteRetro(
            @PathVariable("retroId") Long retroId,
            @RequestParam("memberIds") List<Long> memberIds
    ) {

        retroService.deleteRetroMember(retroId, memberIds);
    }
}
