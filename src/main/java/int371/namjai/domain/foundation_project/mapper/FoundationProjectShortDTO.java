package int371.namjai.domain.foundation_project.mapper;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class FoundationProjectShortDTO {

    private String projectUUID;
    private String projectName;
    private String ProjectDetail;
    private long goal;
    private long received;
    private String status;
    private double percentage;
}
