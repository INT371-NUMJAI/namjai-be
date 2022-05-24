package int371.namjai.domain.resource;

import int371.namjai.domain.foundation.Foundation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "resources")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Resource {
    @Id
    @Column(name = "resource_uuid")
    private String  resourceUUid;

    @Column(name = "file_name")
    private String fileName;

    @Column(name="path_name")
    private String pathName;


}
