package int371.namjai.domain.foundation;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import int371.namjai.domain.foundation_document.FoundationDocuments;
import int371.namjai.domain.foundation_document.FoundationDocumentsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoundationService {

    @Autowired
    private FoundationRepository foundationRepository;

    @Autowired
    private FoundationDocumentsRepo foundationDocumentsRepo;


    public Foundation getFoundationById(String fdnUUid) {
        return foundationRepository.findById(fdnUUid)
                .orElseThrow(FoundationNotFoundException::new);
    }

    public FoundationDocuments getFoundationDocFIle(String fdnUUid){
        return foundationDocumentsRepo.findByFoundationUUid(fdnUUid).orElseThrow(FoundationNotFoundException::new);
    }

    public Foundation convertJsonStringToMovie(String jsonString) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Foundation newFoundation = mapper.readValue(jsonString, Foundation.class);
        return newFoundation;
    }

}
