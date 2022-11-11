package int371.namjai.domain.foundation_project_progress;

import int371.namjai.domain.foundation_project_progress.dto.FoundationProjectProgressDisplayDTO;
import int371.namjai.domain.foundation_project_progress.dto.FoundationProjectProgressFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/view")
@CrossOrigin("*")
public class FoundationProjectProgressController {
    @Autowired
    private FoundationProjectProgressService foundationProjectProgressService;


    @PostMapping(value = "/project/progress")
    public ResponseEntity<Void> createProgressUpdateForFoundationProject(@RequestBody FoundationProjectProgressFormDTO fdnProjectProgress) {
        foundationProjectProgressService.createUpdateProgress(fdnProjectProgress);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/project/{id}/progress")
    public ResponseEntity<List<FoundationProjectProgressDisplayDTO>> getProgressOfFoundationProject(@PathVariable("id") String fdnProjectUUID) {
        return ResponseEntity.ok().body(foundationProjectProgressService.getFoundationProjectProgressDisplayDTListOByID(fdnProjectUUID));
    }
}
