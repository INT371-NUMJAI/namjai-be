package int371.namjai.domain.foundation_project;


import int371.namjai.domain.foundation.Foundation;
import int371.namjai.domain.foundation.FoundationService;
import int371.namjai.domain.foundation_project.mapper.FoundationProjectDTO;
import int371.namjai.domain.foundation_project.mapper.FoundationProjectShortDTO;
import int371.namjai.domain.target_catergories.TargetCategories;
import int371.namjai.domain.target_catergories.TargetCategoriesRepository;
import int371.namjai.utill.ResourceUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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


    @GetMapping(value = "/short/target")
    public ResponseEntity<List<TargetCategories>> getAllTargetCategories() {
        return ResponseEntity.ok(targetCategoriesRepo.findAll());
    }


    @GetMapping(value = "/foundationprojects/{id}")
    public ResponseEntity<FoundationProjectDTO> getFoundationProjectByID(@PathVariable("id") String fdnProjectUUID) {
        return ResponseEntity.ok(foundationProjectService.getFoundationProjectDTOById(fdnProjectUUID));
    }

    @PostMapping("/foundation/project/upload-pic")
    public ResponseEntity<String> uploadFileWithBody(@RequestParam("file") MultipartFile file, @RequestParam("fdnName") String fdnName, @RequestParam("fdnProjectUUID") String fdnProjectUUID) throws IOException {
        String path = resourceUtilService.saveFileToProjectFolder(file, fdnName);
        String fileName = file.getOriginalFilename();
//        FoundationDocuments fdnDoc = new FoundationDocuments(newFDNDocUUid, fileName, Constant.FDN_DOC_PATH, fileExtension, fdnUuid,new Timestamp(date.getTime()));
        FoundationProject fdnProject = foundationProjectService.getFoundationById(fdnProjectUUID);
        fdnProject.setPicturePath(path + "/" + fileName);
        foundationProjectRepo.save(fdnProject);
        return ResponseEntity.ok().body(path + "/" + fileName);
    }

    @PostMapping(value = "/project/create")
    public ResponseEntity<String> uploadFoundationProjectForm(@RequestBody APIFoundationProjectForm apiFoundationProjectForm) {
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

        return ResponseEntity.ok("test demo");

    }


}
