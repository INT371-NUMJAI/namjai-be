package int371.namjai.utill.web_resources;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "web_resources")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WebResources {
    @Id
    @Column(name = "resource_uuid")
    private String resourceUUID;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "create_date")
    private Timestamp createDate;
}
