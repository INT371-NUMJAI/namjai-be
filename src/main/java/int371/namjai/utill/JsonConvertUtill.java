package int371.namjai.utill;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class JsonConvertUtill {

    public String stringUUid(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectUUid object = objectMapper.readValue(json, ObjectUUid.class);
        return object.getUuid();

    }
}
