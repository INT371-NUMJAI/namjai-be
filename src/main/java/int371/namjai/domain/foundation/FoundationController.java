package int371.namjai.domain.foundation;

import int371.namjai.domain.foundation.mapper.APIFDNShort;
import int371.namjai.domain.foundation_document.FoundationDocuments;
import int371.namjai.domain.foundation_document.FoundationDocumentsRepo;
import int371.namjai.domain.security_auth.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

@RestController
@CrossOrigin("*")
//@RequestMapping("/")
public class FoundationController {

    @Autowired
    private FoundationService foundationService;

    @Autowired
    private FoundationDocumentsRepo foundationDocumentsRepo;

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping(value = "/view/foundation/{id}")
    @ResponseBody
    private ResponseEntity<Foundation> getFoundationById(@PathVariable("id") String fdnUUid) {
        Foundation foundation = foundationService.getFoundationById(fdnUUid);
        return ResponseEntity.ok(foundation);
    }

    @GetMapping(value = "/view/foundationlist")
    private List<APIFDNShort> getShortFoundation() {
        List<APIFDNShort> apifdnShort = foundationService.getAPIFDNShort();
        return apifdnShort;
    }

    @GetMapping(value = "/view/getName")
//    public String getNameOnProfileDisplay(@RequestBody APIGetName apiGetName) {
    public String getNameOnProfileDisplay(@RequestParam String userUUID) {
        return authenticationService.getProfileNameDisplay(userUUID);
//        return authenticationService.getProfileNameDisplay(apiGetName.getRole(), apiGetName.getEmail());
    }


    @GetMapping(value = "/view/getFile/{id}")
    public ResponseEntity<Resource> getDocFile(@PathVariable("id") String fdnUUID) throws FileNotFoundException {
        FoundationDocuments foundationDocuments = foundationDocumentsRepo.findByFoundationUUid(fdnUUID).orElseThrow(FoundationNotFoundException::new);
        String fullPath = foundationDocuments.getFilePath() + fdnUUID + "/" + foundationDocuments.getFileName();
        File downloadFile = new File(fullPath);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(downloadFile));
        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + downloadFile.getName());
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");
        return ResponseEntity.ok()
                .headers(header)
                .contentLength(downloadFile.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }
}
