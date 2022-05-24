package int371.namjai.domain.foundation;

import int371.namjai.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
//@RequestMapping("/")
public class FoundationController {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private FoundationService foundationService;

    @GetMapping(value = "/view/foundation/{id}")
    @ResponseBody
    private ResponseEntity<Foundation> getFoundationById(@PathVariable("id") String fdnUUid) {
        Foundation foundation = foundationService.getFoundationById(fdnUUid);
        return ResponseEntity.ok(foundation);
    }


}
