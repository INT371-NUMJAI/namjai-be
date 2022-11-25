package int371.namjai.domain.backoffice;

import int371.namjai.domain.foundation.mapper.APIVerificationFDN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;


@RestController
@RequestMapping("/backoffice")
@CrossOrigin("*")
public class BackOfficeController {

    @Autowired
    private BackOfficeService backOfficeService;


    @PostMapping(value = "/approve-foundation")
    private ResponseEntity<Void> approveFoundation(@RequestBody APIVerificationFDN apiVerificationFDN) throws MessagingException {
        backOfficeService.approveFoundation(apiVerificationFDN);
        return ResponseEntity.ok().build();
    }


}
