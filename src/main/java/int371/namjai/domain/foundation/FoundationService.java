package int371.namjai.domain.foundation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FoundationService {

    @Autowired
    private FoundationRepository foundationRepository;


//   public Optional<Foundation> getFoundationById(String fdnUUid){
//       try {
//           Foundation fdnResponse = foundationRepository.findById(fdnUUid)
//                   .orElseThrow(new )
//       }
//
//   }


}
