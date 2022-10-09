package int371.namjai.domain.foundation;

import int371.namjai.domain.foundation.mapper.APIFDNList;
import int371.namjai.domain.foundation.mapper.APIFDNShort;
import int371.namjai.domain.foundation_document.FoundationDocuments;
import int371.namjai.domain.foundation_document.FoundationDocumentsRepo;
import int371.namjai.domain.security_auth.AuthenticationService;
import int371.namjai.utill.ResourceUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
//@RequestMapping("/")
public class FoundationController {

    @Autowired
    private FoundationService foundationService;

    @Autowired
    private FoundationRepository foundationRepo;

    @Autowired
    private FoundationDocumentsRepo foundationDocumentsRepo;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private ResourceUtilService resourceUtilService;

    @GetMapping(value = "/view/foundation/{id}")
    private ResponseEntity<Foundation> getFoundationById(@PathVariable("id") String fdnUUid) {
        Foundation foundation = foundationService.getFoundationById(fdnUUid);
        return ResponseEntity.ok(foundation);
    }


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

    @PostMapping("/view/foundation/upload/qr")
    public ResponseEntity<String> uploadQRWithBody(@RequestParam("file") MultipartFile file, @RequestParam("fdnName") String fdnName, @RequestParam("fdnUUID") String fdnUUID) throws IOException {
        String path = resourceUtilService.saveFileToProjectFolder(file, fdnName);
        String fileName = file.getOriginalFilename();
//        FoundationDocuments fdnDoc = new FoundationDocuments(newFDNDocUUid, fileName, Constant.FDN_DOC_PATH, fileExtension, fdnUuid,new Timestamp(date.getTime()));
        Foundation foundation = foundationService.getFoundationById(fdnUUID);
        foundation.setQrCodePath(path + "/" + fileName);
        foundationRepo.save(foundation);
        return ResponseEntity.ok().body(path + "/" + fileName);
    }

    @PostMapping("/view/foundation/upload-profile")
    public ResponseEntity<String> uploadProfilePicWithBody(@RequestParam("file") MultipartFile file, @RequestParam("fdnUUID") String fdnUUID) throws IOException {
        Foundation foundation = foundationService.getFoundationById(fdnUUID);
        String path = resourceUtilService.saveFileToProjectFolder(file, foundation.getNameEn());
        String fileName = file.getOriginalFilename();
//        FoundationDocuments fdnDoc = new FoundationDocuments(newFDNDocUUid, fileName, Constant.FDN_DOC_PATH, fileExtension, fdnUuid,new Timestamp(date.getTime()));
        foundation.setProfilePath(path + "/" + fileName);
        foundationRepo.save(foundation);
        return ResponseEntity.ok().body(path + "/" + fileName);
    }

}
