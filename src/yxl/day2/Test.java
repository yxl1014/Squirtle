package yxl.day2;

import yxl.day2.demo1.CountryServiceImpl;
import yxl.day2.demo2.TimeService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Test {
    public static void main(String[] args) throws ParseException {
        TimeService timeService = new TimeService();
        System.out.println(timeService.getDayFromYear("2019-12-11"));
        System.out.println(timeService.getDayFromTwoDate("2019-1-1", "2019-12-11"));
        System.out.println(timeService.getMoreDay("2020-1-1"));
    }
}
