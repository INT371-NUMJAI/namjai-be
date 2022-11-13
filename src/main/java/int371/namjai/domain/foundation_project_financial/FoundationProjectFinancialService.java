package int371.namjai.domain.foundation_project_financial;

import int371.namjai.domain.foundation_project.FoundationProjectService;
import int371.namjai.domain.foundation_project_financial.dto.FoundationProjectFinancialFormDTO;
import int371.namjai.domain.foundation_project_financial.dto.FoundationProjectFinancialMapper;
import int371.namjai.utill.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class FoundationProjectFinancialService {

    @Autowired
    private FoundationProjectFinancialRepository foundationProjectFinancialRepo;

    @Autowired
    private UtilService utilService;

    @Autowired
    private FoundationProjectService foundationProjectService;

    public void createFinancialPlan(FoundationProjectFinancialFormDTO foundationProjectFinancialFormDTO) {
        FoundationProjectFinancial foundationProjectFinancial = new FoundationProjectFinancial();
        foundationProjectFinancial.setFdnProjectFinancialUUID(foundationProjectFinancialFormDTO.getFinancialPlanUUID());
        foundationProjectFinancial.setFoundationProject(foundationProjectService.getFoundationById(foundationProjectFinancialFormDTO.getFdnProjectUUID()));
        foundationProjectFinancial.setDetail(foundationProjectFinancialFormDTO.getDetail());
        foundationProjectFinancial.setQuantity(foundationProjectFinancialFormDTO.getQuantity());
        foundationProjectFinancial.setAmount(foundationProjectFinancialFormDTO.getAmount());
        foundationProjectFinancial.setCreateDate(new Timestamp(System.currentTimeMillis()));

        foundationProjectFinancialRepo.save(foundationProjectFinancial);
    }

    public List<FoundationProjectFinancialFormDTO> getFoundationProjectFinancialListByProjectUUID(String fdnProjectUUID) {
        return FoundationProjectFinancialMapper.INSTANCE.toFoundationProjectFinancialFormDtoList(foundationProjectFinancialRepo.findFoundationProjectFinancialsByFoundationProject_FdnProjectUUidOrderByCreateDateDesc(fdnProjectUUID));
    }

}
