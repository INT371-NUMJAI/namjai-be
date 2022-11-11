package int371.namjai.domain.foundation_project_progress.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
public class FoundationProjectProgressDisplayDTO {
    private String foundationProjectProgressUUID;
    private String fdnProjectUUID;
    private Date proceedDate;
    private String title;
    private String detail;
    private String picturePath;
}
