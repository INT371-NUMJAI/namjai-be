package int371.namjai.domain.foundation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//@Service
@Component("foundationService")
@Scope("singleton")
public class FoundationService {

    @Autowired
    private FoundationRepository foundationRepository;

//    public FoundationService(FoundationRepository foundationRepository) {
//        this.foundationRepository = foundationRepository;
//    }

    //
//    @Override
//    public IEvent getEventByUuid(UUID uuid) throws EventNotFoundException {
//        return eventRepository.findById(uuid)
//                .orElseThrow(() -> new EventNotFoundException("Event does not exist where uuid is " + uuid.toString()));
//    }
//
    public Foundation getFoundationById(String fdnUUid) {
        return foundationRepository.findById(fdnUUid)
                .orElseThrow(FoundationNotFoundException::new);
//              .orElseThrow(()-> new EntityNotFoundException("Foundation does not exist where uuid is "+fdnUUid));

    }


}
