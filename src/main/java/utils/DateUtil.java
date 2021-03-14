package utils;

import java.time.LocalDate;
import java.time.ZoneId;

public class DateUtil {

    public static String getCurrentDate(){
        LocalDate date = LocalDate.now(ZoneId.of("GMT"));
        int dayOfWeek = date.getDayOfWeek().getValue();
        if(dayOfWeek<=6){
            return date.minusDays(1).toString();
        } else if(dayOfWeek==7){
            return date.minusDays(2).toString();
        }
        return null;
    }
}