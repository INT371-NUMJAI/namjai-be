package int371.namjai.domain.foundation;


import int371.namjai.domain.foundation.mapper.APIFDNShort;
import int371.namjai.domain.foundation.mapper.FoundationMapper;
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


    public List<APIFDNShort> getAPIFDNShort() {
        List<Foundation> foundationList = foundationRepository.findAll();
        List<APIFDNShort> apifdnShort = FoundationMapper.INSTANCE.toAPIFDNShort(foundationList);
        return apifdnShort;
    }

}
