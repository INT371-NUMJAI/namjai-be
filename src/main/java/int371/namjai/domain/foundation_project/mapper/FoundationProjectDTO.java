package int371.namjai.domain.foundation_project.mapper;

import int371.namjai.domain.foundation.mapper.FoundationContactDTO;
import int371.namjai.domain.target_catergories.TargetCategories;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Set;

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
    private Set<TargetCategories> targetCategoriesSet;
    private FoundationContactDTO foundationContactDTO;
}
