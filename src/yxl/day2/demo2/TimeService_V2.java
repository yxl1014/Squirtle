package yxl.day2.demo2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeService_V2 {
    private Calendar calendar;
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public long getDayFromYear(String time) throws ParseException {
        this.calendar = Calendar.getInstance();
        this.calendar.setTime(sdf.parse(time));
        String t = this.calendar.get(Calendar.YEAR) + "-1-1";

        long start = sdf.parse(t).getTime();
        long end = sdf.parse(time).getTime();

        return (end - start) / (1000 * 3600 * 24);
    }

    public long getDayFromTwoDate(String start, String end) throws ParseException {
        return (sdf.parse(end).getTime()-sdf.parse(start).getTime())/(1000 * 3600 * 24);
    }

    public long getMoreDay(String time) throws ParseException {
        this.calendar = Calendar.getInstance();
        this.calendar.setTime(sdf.parse(time));
        String t = this.calendar.get(Calendar.YEAR) + "-12-31";

        long end = sdf.parse(t).getTime();
        long start = sdf.parse(time).getTime();
        return (end - start) / (1000 * 3600 * 24);
    }
}
