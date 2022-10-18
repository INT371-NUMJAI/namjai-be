package int371.namjai.domain.volunteer_registerred;

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

}
