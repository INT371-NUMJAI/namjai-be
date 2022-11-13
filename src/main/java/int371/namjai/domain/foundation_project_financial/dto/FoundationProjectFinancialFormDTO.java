package int371.namjai.domain.foundation_project_financial.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoundationProjectFinancialFormDTO {
    private String fdnProjectUUID;
    private String financialPlanUUID;
    private String detail;
    private String quantity;
    private int amount;
}
