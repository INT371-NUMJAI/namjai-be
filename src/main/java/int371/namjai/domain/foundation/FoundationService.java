package int371.namjai.domain.foundation;


import int371.namjai.domain.foundation.mapper.APIFDNList;
import int371.namjai.domain.foundation.mapper.APIFDNShort;
import int371.namjai.domain.foundation.mapper.FoundationMapper;
import int371.namjai.domain.foundation_document.FoundationDocumentsRepo;
import int371.namjai.domain.user.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoundationService {

    private FoundationRepository foundationRepository;
    private FoundationDocumentsRepo foundationDocumentsRepo;
    private UserService userService;

    public FoundationService(FoundationRepository foundationRepository, FoundationDocumentsRepo foundationDocumentsRepo, UserService userService) {
        this.foundationRepository = foundationRepository;
        this.foundationDocumentsRepo = foundationDocumentsRepo;
        this.userService = userService;
    }

    public Foundation getFoundationById(String fdnUUid) {
        return foundationRepository.findById(fdnUUid)
                .orElseThrow(FoundationNotFoundException::new);
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

//    public List<APIFDNList> searchFoundationListShortByName(String fdnName) {
//        List<Foundation> foundationList = foundationRepository.findFoundationsByFdnNameContainingIgnoreCase(fdnName);
//        List<APIFDNList> apifdnLists = FoundationMapper.INSTANCE.toFDNListShort(foundationList);
//        return apifdnLists;
//    }
}
