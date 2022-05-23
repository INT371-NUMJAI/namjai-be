package int371.namjai.domain.foundation;

import int371.namjai.domain.resource.Resource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "foundations")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Foundation {
    @Id
    @Column(name = "fdn_uuid")
    private String fdnUUid;

    @Column(name = "name",unique = true)
    private String fdnName;

    @Column(name ="address_no")
    private String addressNo;

    @Column(name="street_no")
    private String streetNo;

    @Column(name="street_name")
    private String streetName;

    @Column(name="sub_district")
    private String subDistrict;

    @Column(name="district")
    private String district;

    @Column(name="province")
    private String province;

    @Column(name="postalcode")
    private String  postalCode;

    @Column(name="founder_name")
    private String  founderName;

    @Column(name="fdn_detail")
    private String fdnDetail;

    @Column(name="fdn_size")
    private String fdnSize;

    @Column(name="establish_date")
    private String establishDate;

    @Column(name="email",unique = true)
    private String email;

    @Column(name="contact_num")
    private String contactNumber;

    @Column(name="status")
    private String status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "resource_uuid", referencedColumnName = "resource_uuid")
    private Resource resource;

}
