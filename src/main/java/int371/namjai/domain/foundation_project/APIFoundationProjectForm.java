package int371.namjai.domain.foundation_project;


import int371.namjai.domain.target_catergories.TargetCategories;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
public class APIFoundationProjectForm {
    private String fdnUUID;
    private String fdnProjectUUID;
    private String fdnProjectName;
    private Timestamp startDate;
    private Timestamp endDate;
    private int goal;
    private String fdnProjectDetail;
    private String fdnProjectDetailPlace;
    private Set<TargetCategories> targetCategoriesSet;
    private String responsiblePerson;
}