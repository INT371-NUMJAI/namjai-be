package int371.namjai.utill;

import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Service
public class DateUtill {

    private final Long datetime = System.currentTimeMillis();
    private final Timestamp timestamp = new Timestamp(datetime);

    public String nowDateFormatter() {
        SimpleDateFormat formatter = new SimpleDateFormat(Constant.dateFormatter);
        return formatter.format(System.currentTimeMillis());
    }

    public Timestamp nowDateTimeFormatter() {
//        SimpleDateFormat formatter= new SimpleDateFormat(Constant.dateTimeFormatter);
        return timestamp;
    }

}
