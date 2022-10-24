package int371.namjai.domain.foundation_project.mapper;

import int371.namjai.domain.foundation.mapper.FoundationContactDTO;
import int371.namjai.domain.target_catergories.TargetCategories;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.Set;

@Setter
@Getter
public class FoundationProjectDTO {
    private String foundationProjectUUID;
    private String foundationProjectName;
    private String foundationProjectDetail;
    private String foundationProjectDetailPlace;
    private long goal;
    private long received;
    private double percentage;
    private Date startDate;
    private Date endDate;
    private String picturePath;
    private String status;
    private Set<TargetCategories> targetCategoriesSet;
    private FoundationContactDTO foundationContactDTO;
}
