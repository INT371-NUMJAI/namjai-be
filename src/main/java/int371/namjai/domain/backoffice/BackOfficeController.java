package int371.namjai.domain.backoffice;

import int371.namjai.domain.foundation.*;
import int371.namjai.domain.foundation.mapper.APIVerificationFDN;
import int371.namjai.domain.user.User;
import int371.namjai.domain.user.UserRepository;
import int371.namjai.utill.Constant;
import int371.namjai.utill.JsonConvertUtill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;

@RestController
@RequestMapping("/backoffice")
@CrossOrigin("*")
public class BackOfficeController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FoundationRepository foundationRepository;

    @Autowired
    private FoundationService foundationService;

    @Autowired
    private JsonConvertUtill jsonConvertUtill;

    @Autowired
    private BackOfficeService backOfficeService;

    @Autowired
    private FoundationDocumentsRepo foundationDocumentsRepo;

    @GetMapping(value = "/users")
    private List<User> getAllUser() {
        return userRepository.findAll();
    }


    @PostMapping(value = "/approve-foundation")
    @ResponseBody
    private ResponseEntity<Void> approveFoundation(@RequestBody APIVerificationFDN apiVerificationFDN) throws MessagingException {
        Foundation foundation = foundationService.getFoundationById(apiVerificationFDN.getFdnUUid());
        FoundationDocuments foundationDocuments = foundationService.getFoundationDocFIle(apiVerificationFDN.getFdnUUid());
        String newStatus = ("V".equals(apiVerificationFDN.getStatus())) ? Constant.FDN_STATUS_VERIFIED : Constant.FDN_STATUS_REJECTED;
        if(Constant.FDN_STATUS_VERIFIED.equals(newStatus)){
            User newUser = userRepository.findByEmailIgnoreCaseAndStatusDisable(foundation.getEmail());
            newUser.setStatus(Constant.USER_STATUS_ACTIVE);
            foundation.setStatus(newStatus);
            foundationRepository.save(foundation);
            userRepository.save(newUser);
        }
        backOfficeService.sendmail(foundation.getEmail(), newStatus, apiVerificationFDN.getMessage());
        foundationDocumentsRepo.delete(foundationDocuments);
        foundationRepository.delete(foundation);
//        return ResponseEntity.ok(foundation);
        return ResponseEntity.ok().build();
    }


}
