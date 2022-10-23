package int371.namjai.domain.volunteer_registerred;

import int371.namjai.domain.volunteer_registerred.dto.EnrolledListVolunteerProjectDTO;
import int371.namjai.domain.volunteer_registerred.dto.UnEnrolledVolunteerProjectDTO;
import int371.namjai.domain.volunteer_registerred.dto.VolunteerRegisteredUserDTO;
import int371.namjai.domain.volunteer_registerred.dto.VolunteerUnRegisteredUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/view")
@CrossOrigin("*")
public class VolunteerRegisteredController {
    @Autowired
    private VolunteerRegisteredService volunteerRegisteredService;

    @PostMapping(value = "/volunteer/registered-user")
    public ResponseEntity<Void> creatNewVolunteerByRegisteredUser(@RequestBody VolunteerRegisteredUserDTO volunteerRegisteredUserDTO) {
        volunteerRegisteredService.recordVolunteerRegisterByRegisteredUser(volunteerRegisteredUserDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/volunteer/unregistered-user")
    public ResponseEntity<Void> creatNewVolunteerByUnRegisteredUser(@RequestBody VolunteerUnRegisteredUserDTO volunteerUnRegisteredUserDTO) {
        volunteerRegisteredService.recordVolunteerRegisterByUnregisteredUser(volunteerUnRegisteredUserDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/volunteer/{id}/enrolledlist")
    public ResponseEntity<EnrolledListVolunteerProjectDTO> getRegisteredUserVolunteerProject(@PathVariable("id") String volunteerProjectUUID) {
        return ResponseEntity.ok().body(volunteerRegisteredService.getListOfEnRolledUserInVolunteerProject(volunteerProjectUUID));
    }

    @DeleteMapping("/volunteer-enrolled/remove")
    public ResponseEntity<Void> removeEnrolledVolunteerProjectMember(@RequestBody UnEnrolledVolunteerProjectDTO unEnrolledVolunteerProjectDTO) {
        volunteerRegisteredService.unRegisteredVolunteerProject(unEnrolledVolunteerProjectDTO);
        return ResponseEntity.ok().build();
    }

    //    checkUserIsEnrolledOrNot
    @GetMapping("/volunteer/check-enrolled")
    public ResponseEntity<Boolean> getRegisteredUserVolunteerProject(@RequestParam("volunteerid") String volunteerProjectUUID, @RequestParam("email") String useremail) {
        return ResponseEntity.ok().body(volunteerRegisteredService.checkUserIsEnrolledOrNot(volunteerProjectUUID, useremail));
    }

}
