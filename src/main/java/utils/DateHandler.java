package utils;

import java.time.LocalDate;
import java.time.ZoneId;

public class DateHandler {

    /**
     * If the date is not explicitly set then the date will default to current date. The assertions for date in the
     * steps definition class will happen based on this date
     */
    public String date = getCurrentDate();

    public DateHandler setDate(String date) {
        this.date = date;
        return this;
    }

    /**
     * Gives the current date; excludes the date belonging to weekends
     *
     * @return current date that doesn't fall on Saturday's or Sunday's
     */
    private static String getCurrentDate(){
        LocalDate date = LocalDate.now(ZoneId.of("GMT"));
        int dayOfWeek = date.getDayOfWeek().getValue();
        if(dayOfWeek<=6 && dayOfWeek!=1){
            return date.minusDays(1).toString();
        } else if(dayOfWeek==7){
            return date.minusDays(2).toString();
        } else if(dayOfWeek==1){
            return date.minusDays(3).toString();
        }
        return null;
    }
}
