package int371.namjai.domain.foundation_project.mapper;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoundationProjectListToRequest {
    private String foundationProjectUUID;
    private String foundationProjectName;
    private double totalAmount;
    private String label;

    public String getLabel() {
        return this.getFoundationProjectName();
    }
}
