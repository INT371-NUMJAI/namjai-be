package int371.namjai.domain.volunteer_registerred;

import int371.namjai.domain.volunteer_registerred.dto.EnrolledListVolunteerProjectDTO;
import int371.namjai.domain.volunteer_registerred.dto.UserEnrolledVolunteerProjectDTO;
import int371.namjai.domain.volunteer_registerred.dto.VolunteerRegisteredUserDTO;
import int371.namjai.domain.volunteer_registerred.dto.VolunteerUnRegisteredUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @DeleteMapping(value = "/volunteer-enrolled/remove/{email}/{id}")
    public ResponseEntity<Void> removeEnrolledVolunteerProjectMember(@PathVariable("email") String email, @PathVariable("id") String volunteerProjectUUID) {
        volunteerRegisteredService.unRegisteredVolunteerProject(email, volunteerProjectUUID);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/volunteer/check-enrolled")
    public ResponseEntity<Boolean> getRegisteredUserVolunteerProject(@RequestParam("volunteerid") String volunteerProjectUUID, @RequestParam("email") String useremail) {
        return ResponseEntity.ok().body(volunteerRegisteredService.checkUserIsEnrolledOrNot(volunteerProjectUUID, useremail));
    }

    @GetMapping(value = "/volunteer-enrolled/activities")
    public ResponseEntity<List<UserEnrolledVolunteerProjectDTO>> getListOfVolunteerEnrolledUserByUserEmail(@RequestParam("email") String email) {
        return ResponseEntity.ok().body(volunteerRegisteredService.getActivityJoinedVolunteerProjectsByEmail(email));
    }
}
