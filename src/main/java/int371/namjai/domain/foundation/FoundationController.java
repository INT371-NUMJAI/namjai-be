package int371.namjai.domain.foundation;

import int371.namjai.domain.auth_security.AuthenticationService;
import int371.namjai.domain.foundation.mapper.APIFDNList;
import int371.namjai.domain.foundation.mapper.APIFDNShort;
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
import java.util.Map;

@RestController
@CrossOrigin("*")
public class FoundationController {

    @Autowired
    private FoundationService foundationService;



    @Autowired
    private AuthenticationService authenticationService;



    @GetMapping(value = "/view/foundationlist")
    private List<APIFDNShort> getShortFoundation() {
        List<APIFDNShort> apifdnShort = foundationService.getAPIFDNShort();
        return apifdnShort;
    }

    @GetMapping(value = "/view/foundationlist/short")
    private List<APIFDNList> getShortFoundationList() {
        List<APIFDNList> apifdnShortList = foundationService.getFoundationListShort();
        return apifdnShortList;
    }

    @GetMapping(value = "/view/getName")
    public Map<String, String> getNameOnProfileDisplay(@RequestParam String email) {
        return authenticationService.getProfileNameDisplay(email);
    }


    @GetMapping(value = "/view/getFile/{id}")
    public ResponseEntity<Resource> getDocFile(@PathVariable("id") String fdnUUID) throws FileNotFoundException {
        File downloadFile = new File(foundationService.retrievedDocFullPath(fdnUUID));
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
