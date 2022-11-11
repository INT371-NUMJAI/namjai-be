package int371.namjai.domain.foundation_project_progress;

import int371.namjai.domain.foundation_project.FoundationProject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "fdn_project_progress")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FoundationProjectProgress {
    @Id
    @Column(name = "fdn_project_progress_uuid")
    private String fdnProjectProgressUUID;

    @OneToOne
    @JoinColumn(name = "fdn_project_uuid", referencedColumnName = "fdn_project_uuid")
    private FoundationProject foundationProject;

    @Column(name = "title")
    private String title;

    @Column(name = "detail")
    private String detail;

    @Column(name = "picture_path")
    private String picturePath;

    @Column(name = "proceed_date")
    private Date proceedDate;

    @Column(name = "create_date")
    private Timestamp createDate;
}
