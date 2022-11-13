package int371.namjai.domain.foundation_project_financial;

import int371.namjai.domain.foundation_project_financial.dto.FoundationProjectFinancialFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/view")
@CrossOrigin("*")
public class FoundationProjectFinancialController {
    @Autowired
    private FoundationProjectFinancialService foundationProjectFinancialService;


    @PostMapping("/financial")
    public ResponseEntity<Void> uploadFinancialToEntity(@RequestBody FoundationProjectFinancialFormDTO financialDTO) {
        foundationProjectFinancialService.createFinancialPlan(financialDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{id}/financial")
    public ResponseEntity<List<FoundationProjectFinancialFormDTO>> getFinancialListByFdnProjectUUID(@PathVariable("id") String fdnProjectUUID) {
        return ResponseEntity.ok().body(foundationProjectFinancialService.getFoundationProjectFinancialListByProjectUUID(fdnProjectUUID));
    }


}
