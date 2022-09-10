package int371.namjai.domain.foundation_project;


import int371.namjai.domain.foundation_project.mapper.FoundationProjectDTO;
import int371.namjai.domain.foundation_project.mapper.FoundationProjectShortDTO;
import int371.namjai.domain.target_catergories.TargetCategories;
import int371.namjai.domain.target_catergories.TargetCategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/view")
@CrossOrigin("*")
public class FoundationProjectController {

    @Autowired
    private FoundationProjectService foundationProjectService;

    @Autowired
    private TargetCategoriesRepository targetCategoriesRepo;

    @GetMapping(value = "/foundationprojects")
    public ResponseEntity<List<FoundationProject>> getAllFoundationProjects() {
        return ResponseEntity.ok(foundationProjectService.getAllFoundationProjects());
    }

    @GetMapping(value = "/short/foundationprojects")
    public ResponseEntity<List<FoundationProjectShortDTO>> getAllFoundationProjectsInShort() {
        return ResponseEntity.ok(foundationProjectService.getAllFoundationProjectInShort());
    }


    @GetMapping(value = "/short/target")
    public ResponseEntity<List<TargetCategories>> getAllTargetCategories() {
        return ResponseEntity.ok(targetCategoriesRepo.findAll());
    }


    @GetMapping(value = "/foundationprojects/{id}")
    public ResponseEntity<FoundationProjectDTO> getFoundationProjectByID(@PathVariable("id") String fdnProjectUUID) {
        return ResponseEntity.ok(foundationProjectService.getFoundationProjectDTOById(fdnProjectUUID));
    }


}
