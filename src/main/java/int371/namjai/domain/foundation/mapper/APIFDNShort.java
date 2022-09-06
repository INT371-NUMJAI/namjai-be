package int371.namjai.domain.foundation.mapper;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class APIFDNShort {
     private String fdnName;
     private Timestamp createDate;
     private String status;
     private String approval;
}
