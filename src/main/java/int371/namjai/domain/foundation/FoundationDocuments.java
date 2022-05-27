package int371.namjai.domain.foundation;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "fdns_resources_documents")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FoundationDocuments {

    @Id
    @Column(name = "fdn_resources_doc_uuid")
    private String fdnDocUUid;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "file_type")
    private String fileType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fdn_uuid")
    private Foundation foundation;


}
