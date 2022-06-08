package yxl.day2.demo2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeService {
    private Calendar calendar;
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public int getDayFromYear(String time) throws ParseException {
        //new一个Calender对象
        this.calendar = Calendar.getInstance();

        //将时间格式化放入对象
        this.calendar.setTime(sdf.parse(time));
        //获取今天是第几天
        int i = this.calendar.get(Calendar.DAY_OF_YEAR);

        //System.out.println("当前年份第几天：" + i);
        //返回过了多少天
        return i-1;
    }

    public int getDayFromTwoDate(String start, String end) throws ParseException {
        int i = 0;

        //new一个Calender对象
        this.calendar = Calendar.getInstance();
        //将开始日期传入
        this.calendar.setTime(sdf.parse(start));
        // 获取年月日
        int y = this.calendar.get(Calendar.YEAR);
        int m = this.calendar.get(Calendar.MONTH);
        int d = this.calendar.get(Calendar.DATE);
        //将结束时间传入
        this.calendar.setTime(sdf.parse(end));

        //循环获取值
        while (true) {
            //将结束时间往前推一天
            this.calendar.add(Calendar.DATE, -1);
            //如果年月日等于开始时间
            if (this.calendar.get(Calendar.MONTH) == m && this.calendar.get(Calendar.YEAR) == y && this.calendar.get(Calendar.DATE) == d) {
                //则退出
                break;
            }
            i++;
        }
        //System.out.println(i);
        //返回中间隔了多少天
        return i;
    }

    public int getMoreDay(String time) throws ParseException {
        //new一个Calender对象
        this.calendar = Calendar.getInstance();
        //将时间传入
        this.calendar.setTime(sdf.parse(time));
        //获取当前是第多少天
        int i = this.calendar.get(Calendar.DAY_OF_YEAR);
        //System.out.println("当前年份第几天：" + i);

        //将这一年的最后一天传入
        this.calendar.setTime(sdf.parse(this.calendar.get(Calendar.YEAR)+"-12-31"));

        //System.out.println(sdf.format(this.calendar.getTime()));
        //获取这一年共多少天
        int last=this.calendar.get(Calendar.DAY_OF_YEAR);
        //用最后一天减去现在是第几天获得还差多少天
        i=last -i;
        //System.out.println("还剩：" + i + "天");
        return i;
    }
}
