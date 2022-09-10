package int371.namjai.domain.foundation_project.mapper;

import int371.namjai.domain.foundation.mapper.FoundationContactDTO;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
public class FoundationProjectDTO {
    private String foundationProjectUUID;
    private String foundationProjectName;
    private String foundationProjectDetail;
    private String foundationProjectDetailPlace;
    private Integer goal;
    private Timestamp startDate;
    private Timestamp endDate;
    private FoundationContactDTO foundationContactDTO;
}
