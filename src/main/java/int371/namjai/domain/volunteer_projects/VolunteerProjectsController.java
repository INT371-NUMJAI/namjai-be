package int371.namjai.domain.volunteer_projects;

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
    public ResponseEntity<List<VolunteerProjects>> getVolunteerProjectList() {
        return ResponseEntity.ok().body(volunteerProjectsService.getVolunteerProjectsList());
    }

    @PostMapping("/volunteer-projects/add")
    public ResponseEntity<Void> createNewVolunteerProject(@RequestBody VolunteerProjects volunteerProjects) {
        volunteerProjectsService.createVolunteerProjects(volunteerProjects);
        return ResponseEntity.ok().build();
    }
}
