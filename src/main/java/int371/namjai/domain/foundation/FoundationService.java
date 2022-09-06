package int371.namjai.domain.foundation;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import int371.namjai.domain.foundation.mapper.APIFDNShort;
import int371.namjai.domain.foundation.mapper.FoundationMapper;
import int371.namjai.domain.foundation_document.FoundationDocuments;
import int371.namjai.domain.foundation_document.FoundationDocumentsRepo;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

@Service
public class FoundationService {

    private FoundationRepository foundationRepository;
    private FoundationDocumentsRepo foundationDocumentsRepo;
//    FoundationMapper foundationMapper;

    public FoundationService(FoundationRepository foundationRepository, FoundationDocumentsRepo foundationDocumentsRepo) {
        this.foundationRepository = foundationRepository;
        this.foundationDocumentsRepo = foundationDocumentsRepo;
    }

    public Foundation getFoundationById(String fdnUUid) {
        return foundationRepository.findById(fdnUUid)
                .orElseThrow(FoundationNotFoundException::new);
    }

    public void getFoundationDocFIle(String fdnUUid) throws IOException {
//        public static void getPDF(String sourceURL, String filePathToStore) throws IOException {
//            URL url = new URL(sourceURL);
//            File file = new File(filePathToStore);
//            FileUtils.copyURLToFile(url, file);
//        }
        FoundationDocuments foundationDocuments = foundationDocumentsRepo.findByFoundationUUid(fdnUUid).orElseThrow(FoundationNotFoundException::new);
        URL url = new URL("localhost:500" + fdnUUid);
//        URL url = new URL("https://www.namjai.site/fdn/"+fdnUUid);
        File file = new File(foundationDocuments.getFilePath() + fdnUUid + foundationDocuments.getFileName());
        FileUtils.copyURLToFile(url, file);
//        return foundationDocumentsRepo.findByFoundationUUid(fdnUUid).orElseThrow(FoundationNotFoundException::new);
//        return null;
    }

    public Foundation convertJsonStringToMovie(String jsonString) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Foundation newFoundation = mapper.readValue(jsonString, Foundation.class);
        return newFoundation;
    }

    //    public List<APIFDNShort> getAPIFDNShort(String fdnUUID){
    public List<APIFDNShort> getAPIFDNShort() {
//        Foundation foundation = getFoundationById(fdnUUID);
        List<Foundation> foundationList = foundationRepository.findAll();
        List<APIFDNShort> apifdnShort = FoundationMapper.INSTANCE.toAPIFDNShort(foundationList);
        return apifdnShort;
    }

}
