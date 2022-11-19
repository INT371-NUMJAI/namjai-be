package int371.namjai.domain.volunteer_projects;

import int371.namjai.domain.volunteer_projects.mapper.VolunteerProjectDetailDTO;
import int371.namjai.domain.volunteer_projects.mapper.VolunteerProjectShort;
import int371.namjai.domain.volunteer_projects.mapper.VolunteerProjectsFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/view")
@CrossOrigin("*")
public class VolunteerProjectsController {
    @Autowired
    private VolunteerProjectsService volunteerProjectsService;

    @GetMapping("/volunteer-projects")
    public ResponseEntity<List<VolunteerProjectShort>> getVolunteerProjectList() {
        return ResponseEntity.ok().body(volunteerProjectsService.getVolunteerProjectsList());
    }

    @GetMapping("/volunteer-projects/search")
    public ResponseEntity<List<VolunteerProjectShort>> searchVolunteerProjectList(@RequestParam("q") String volunteerName) {
        return ResponseEntity.ok().body(volunteerProjectsService.getVolunteerProjectsListByName(volunteerName));
    }


    @PostMapping("/volunteer-projects/add")
    public ResponseEntity<Void> createNewVolunteerProject(@RequestBody VolunteerProjectsFormDTO volunteerProjects) {
        volunteerProjectsService.createVolunteerProjects(volunteerProjects);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/volunteer-projects-detail/{id}")
    public ResponseEntity<VolunteerProjectDetailDTO> getVolunteerProjectDetail(@PathVariable("id") String volunteerProjectUUID) {
        return ResponseEntity.ok().body(volunteerProjectsService.getVolunteerProjectDetailByUUID(volunteerProjectUUID));
    }

    @GetMapping("/volunteer-projects/")
    public ResponseEntity<List<VolunteerProjectShort>> getVolunteerProjectListByFDNEmail(@RequestParam("email") String fdnEmail) {
        return ResponseEntity.ok().body(volunteerProjectsService.getVolunteerProjectsListByFDNEmail(fdnEmail));
    }

    @GetMapping("/volunteer-projects/user-fdn")
    public ResponseEntity<List<VolunteerProjectShort>> getVolunteerProjectListByFDNEmailAndStatusOpen(@RequestParam("email") String fdnEmail) {
        return ResponseEntity.ok().body(volunteerProjectsService.getVolunteerProjectsListByFDNEmailAndStatusOpen(fdnEmail));
    }

    @GetMapping("/volunteer-projects/target")
    public ResponseEntity<List<VolunteerProjectShort>> getVolunteerProjectListByTargetCat(@RequestParam("targetCatName") String targetCatName) {
        return ResponseEntity.ok().body(volunteerProjectsService.getVolunteerProjectsListByTargetCatName(targetCatName));
    }

    @GetMapping("/volunteer-projects/user-suggestion/{email}")
    public ResponseEntity<List<VolunteerProjectShort>> getVolunteerProjectsListByUserSuggestion(@PathVariable("email") String userEmail) {
        return ResponseEntity.ok().body(volunteerProjectsService.getVolunteerProjectUserSuggestionByUserEmail(userEmail));
    }

    //   getVolunteerProjectTop6Random
    @GetMapping("/volunteer-projects/random")
    public ResponseEntity<List<VolunteerProjectShort>> getVolunteerProjectsListTop6Random() {
        return ResponseEntity.ok().body(volunteerProjectsService.getVolunteerProjectTop6Random());
    }

}
