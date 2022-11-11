package int371.namjai.domain.foundation_project;


import int371.namjai.domain.foundation.Foundation;
import int371.namjai.domain.foundation.FoundationService;
import int371.namjai.domain.foundation_project.mapper.FoundationProjectDTO;
import int371.namjai.domain.foundation_project.mapper.FoundationProjectListToRequest;
import int371.namjai.domain.foundation_project.mapper.FoundationProjectMapper;
import int371.namjai.domain.foundation_project.mapper.FoundationProjectShortDTO;
import int371.namjai.domain.target_catergories.TargetCategories;
import int371.namjai.domain.target_catergories.TargetCategoriesRepository;
import int371.namjai.utill.ResourceUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
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

    @Autowired
    private FoundationService foundationService;

    @Autowired
    private ResourceUtilService resourceUtilService;


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
    public ResponseEntity<List<FoundationProjectShortDTO>> getAllFoundationProjectsInShortByTargetID(@RequestParam String targetCatID) {
        return ResponseEntity.ok(foundationProjectService.getAllFoundationProjectInShortByTargetCatID(targetCatID));
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
        FoundationProject foundationProject = new FoundationProject();
        Foundation foundation = foundationService.getFoundationById(apiFoundationProjectForm.getFdnUUID());
        foundationProject.setFoundation(foundation);
        foundationProject.setFdnProjectUUid(apiFoundationProjectForm.getFdnProjectUUID());
        foundationProject.setFdnProjectName(apiFoundationProjectForm.getFdnProjectName());
        foundationProject.setStartDate(apiFoundationProjectForm.getStartDate());
        foundationProject.setEndDate(apiFoundationProjectForm.getEndDate());
        foundationProject.setGoal(apiFoundationProjectForm.getGoal());
        foundationProject.setCreateDate(new Timestamp(System.currentTimeMillis()));
        foundationProject.setPicturePath(null);
        foundationProject.setFdnProjectDetail(apiFoundationProjectForm.getFdnProjectDetail());
        foundationProject.setFdnProjectDetailPlace(apiFoundationProjectForm.getFdnProjectDetailPlace());
        foundationProject.setTargetCategoriesSet(apiFoundationProjectForm.getTargetCategoriesSet());
        foundationProject.setResponsiblePerson(apiFoundationProjectForm.getResponsiblePerson());
        foundationProject.setReceived(0L);
        foundationProject.setStatus(apiFoundationProjectForm.getStatus());
        foundationProjectRepo.save(foundationProject);
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
}
