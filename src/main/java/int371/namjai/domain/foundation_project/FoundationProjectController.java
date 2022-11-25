package int371.namjai.domain.foundation_project;


import int371.namjai.domain.foundation_project.mapper.FoundationProjectDTO;
import int371.namjai.domain.foundation_project.mapper.FoundationProjectListToRequest;
import int371.namjai.domain.foundation_project.mapper.FoundationProjectMapper;
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
    private FoundationProjectRepository foundationProjectRepo;

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


    @GetMapping(value = "/short/foundationproject/search/")
    public ResponseEntity<List<FoundationProjectShortDTO>> getAllFoundationProjectsInShort(@RequestParam("q") String fdnName) {

        return ResponseEntity.ok(foundationProjectService.getFoundationProjectByName(fdnName));
    }


    @GetMapping(value = "/random")
    public ResponseEntity<List<FoundationProjectShortDTO>> getRandomFoundationProjectsInShort() {
        return ResponseEntity.ok(FoundationProjectMapper.INSTANCE.toFoundationProjectShortDtoList(foundationProjectRepo.findTop6AndStatusOpen()));
    }

    @GetMapping(value = "/foundationproject/target")
    public ResponseEntity<List<FoundationProjectShortDTO>> getAllFoundationProjectsInShortByTargetID(@RequestParam String targetCatName) {
        return ResponseEntity.ok(foundationProjectService.getAllFoundationProjectInShortByTargetCatName(targetCatName));
    }


    @GetMapping(value = "/short/target")
    public ResponseEntity<List<TargetCategories>> getAllTargetCategories() {
        return ResponseEntity.ok(targetCategoriesRepo.findAll());
    }


    @GetMapping(value = "/foundationprojects/{id}")
    public ResponseEntity<FoundationProjectDTO> getFoundationProjectByID(@PathVariable("id") String fdnProjectUUID) {
        return ResponseEntity.ok(foundationProjectService.getFoundationProjectDTOById(fdnProjectUUID));
    }


    @PostMapping(value = "/project/create")
    public ResponseEntity<Void> uploadFoundationProjectForm(@RequestBody APIFoundationProjectForm apiFoundationProjectForm) {
        foundationProjectService.createFoundationProject(apiFoundationProjectForm);
        return ResponseEntity.ok().build();
    }


    @PostMapping(value = "/foundationproject/edit")
    public ResponseEntity<Void> updateFoundationProject(@RequestBody APIEditStatusProjectStatus apiEditStatusProjectStatus) {
        foundationProjectService.editFoundationProjectStatus(apiEditStatusProjectStatus);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/project/user")
    public ResponseEntity<List<FoundationProjectShortDTO>> getFdnProjectByFdnUUID(@RequestParam("email") String email) {
        List<FoundationProject> foundationProjectList = foundationProjectRepo.findByFDNEmailIgnoreCase(email);
        List<FoundationProjectShortDTO> foundationProjectShortDTOS = FoundationProjectMapper.INSTANCE.toFoundationProjectShortDtoList(foundationProjectList);
        return ResponseEntity.ok().body(foundationProjectShortDTOS);
    }

    @GetMapping(value = "/projects/user-fdn")
    public ResponseEntity<List<FoundationProjectShortDTO>> getFdnProjectByFdnUUIDStatusOpen(@RequestParam("email") String email) {
        List<FoundationProject> foundationProjectList = foundationProjectRepo.findByFDNEmailIgnoreCaseAndStatusOpen(email);
        List<FoundationProjectShortDTO> foundationProjectShortDTOS = FoundationProjectMapper.INSTANCE.toFoundationProjectShortDtoList(foundationProjectList);
        return ResponseEntity.ok().body(foundationProjectShortDTOS);
    }

    @GetMapping(value = "/project/user-open")
    public ResponseEntity<List<FoundationProjectShortDTO>> getFdnProjectByFdnUUIDAndStatusNotClosed(@RequestParam("email") String email) {
        List<FoundationProject> foundationProjectList = foundationProjectRepo.findByFDNEmailIgnoreCaseAndStatusNotClosed(email);
        List<FoundationProjectShortDTO> foundationProjectShortDTOS = FoundationProjectMapper.INSTANCE.toFoundationProjectShortDtoList(foundationProjectList);
        return ResponseEntity.ok().body(foundationProjectShortDTOS);
    }

    @GetMapping(value = "/projects/closed")
    public ResponseEntity<List<FoundationProjectListToRequest>> getProjectListToMakeRequest(@RequestParam("fdnid") String fdnUUID) {
        return ResponseEntity.ok().body(foundationProjectService.getFoundationProjectToRequest(fdnUUID));
    }

    @GetMapping(value = "/projects/user-suggestion/{email}")
    public ResponseEntity<List<FoundationProjectShortDTO>> getUserSuggestionFoundationProjects(@PathVariable("email") String userEmail) {
        return ResponseEntity.ok().body(FoundationProjectMapper.INSTANCE.toFoundationProjectShortDtoList(foundationProjectService.getFoundationProjectsUserSuggestionByUserEmail(userEmail)));
    }
}
