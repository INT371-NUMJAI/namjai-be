package int371.namjai.domain.target_catergories;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "target_categories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TargetCategories {

    @Id
    @Column(name = "target_category_id")
    private String targetCategoriesID;

    @Column(name = "target_category_name")
    private String targetCategoriesName;

//    @ManyToOne
//    @JoinColumn(name="fdn_project_uuid", nullable=false)
//    private FoundationProject foundationProject;


}
