package int371.namjai.domain.foundation_project;

import int371.namjai.domain.foundation.Foundation;
import int371.namjai.domain.target_catergories.TargetCategories;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "fdn_projects")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FoundationProject {

    @Id
    @Column(name = "fdn_project_uuid")
    private String fdnProjectUUid;

    @Column(name = "fp_name")
    private String fdnProjectName;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "goal")
    private Integer goal;

    @Column(name = "create_date")
    private Timestamp createDate;

    @ManyToOne
    @JoinColumn(name = "fdn_uuid")
    private Foundation foundation = new Foundation();

    @Column(name = "picture_path")
    private String picturePath;

    @Column(name = "fp_detail")
    private String fdnProjectDetail;

    @Column(name = "fp_detail_place")
    private String fdnProjectDetailPlace;

    @Column(name = "responsible_person")
    private String responsiblePerson;

    @Column(name = "status")
    private String status;

    @ManyToMany
    @JoinTable(
            name = "fdn_project_target_categories",
            joinColumns = @JoinColumn(name = "fdn_project_uuid"),
            inverseJoinColumns = @JoinColumn(name = "target_category_id"))
    Set<TargetCategories> targetCategoriesSet;

}
