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

    @PostMapping("/volunteer-projects/add")
    public ResponseEntity<Void> createNewVolunteerProject(@RequestBody VolunteerProjectsFormDTO volunteerProjects) {
        volunteerProjectsService.createVolunteerProjects(volunteerProjects);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/volunteer-projects-detail/{id}")
    public ResponseEntity<VolunteerProjectDetailDTO> getVolunteerProjectDetail(@PathVariable("id") String volunteerProjectUUID) {
        return ResponseEntity.ok().body(volunteerProjectsService.getVolunteerProjectDetailByUUID(volunteerProjectUUID));
    }


}
