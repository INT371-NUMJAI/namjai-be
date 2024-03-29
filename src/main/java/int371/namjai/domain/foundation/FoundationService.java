package int371.namjai.domain.foundation;


import int371.namjai.domain.foundation.mapper.APIFDNList;
import int371.namjai.domain.foundation.mapper.APIFDNShort;
import int371.namjai.domain.foundation.mapper.FoundationMapper;
import int371.namjai.domain.foundation_document.FoundationDocuments;
import int371.namjai.domain.foundation_document.FoundationDocumentsRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoundationService {

    private FoundationRepository foundationRepository;
    private FoundationDocumentsRepo foundationDocumentsRepo;

    public FoundationService(FoundationRepository foundationRepository, FoundationDocumentsRepo foundationDocumentsRepo) {
        this.foundationRepository = foundationRepository;
        this.foundationDocumentsRepo = foundationDocumentsRepo;
    }

    public Foundation getFoundationById(String fdnUUid) {
        return foundationRepository.findById(fdnUUid)
                .orElseThrow(FoundationNotFoundException::new);
    }

    public String retrievedDocFullPath(String fdnUUID) {
        FoundationDocuments foundationDocuments = foundationDocumentsRepo.findByFoundationUUid(fdnUUID).orElseThrow(FoundationNotFoundException::new);
        return foundationDocuments.getFilePath() + fdnUUID + "/" + foundationDocuments.getFileName();
    }

    public Foundation getFoundationByEmail(String email) {
        return foundationRepository.findByEmailIgnoreCase(email);
    }

    public void saveToTableFoundation(Foundation foundation) {
        foundationRepository.save(foundation);
    }

    public List<APIFDNShort> getAPIFDNShort() {
        List<Foundation> foundationList = foundationRepository.findAll();
        List<APIFDNShort> apifdnShort = FoundationMapper.INSTANCE.toAPIFDNShort(foundationList);
        return apifdnShort;
    }

    public List<APIFDNList> getFoundationListShort() {
        List<Foundation> foundationList = foundationRepository.findFoundationsByStatus();
        List<APIFDNList> apifdnLists = FoundationMapper.INSTANCE.toFDNListShort(foundationList);
        return apifdnLists;
    }


}
