package int371.namjai.utill;

import int371.namjai.domain.security_auth.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/view")
@CrossOrigin("*")
public class UtilController {

    @Autowired
    private AuthenticationService authenticationService;


}
