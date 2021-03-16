package utils;

import java.time.LocalDate;
import java.time.LocalTime;
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
     * Gives the current date; excludes the date belonging to weekends. The current date will be set
     * to previous day's date if the current time is before market closing time
     *
     * @return current date that doesn't fall on Saturday's or Sunday's
     */
    private static String getCurrentDate(){
        LocalTime currentTime = LocalTime.now(ZoneId.of("CET"));
        LocalTime marketClosingTime = LocalTime.of(17,0);
        LocalDate date = LocalDate.now(ZoneId.of("CET"));
        int dayOfWeek = date.getDayOfWeek().getValue();

        if(dayOfWeek==6){
            return date.minusDays(1).toString();
        }else if(dayOfWeek==7){
            return date.minusDays(2).toString();
        }else if (dayOfWeek==1 && currentTime.isBefore(marketClosingTime)){
            return date.minusDays(3).toString();
        }else if(currentTime.isBefore(marketClosingTime)){
            return date.minusDays(1).toString();
        }

        return date.toString();
    }
}
