package yxl.day2;

import yxl.day2.demo1.CountryServiceImpl;
import yxl.day2.demo2.TimeService;
import yxl.day2.demo2.TimeService_V2;
import yxl.day2.demo3.MyByteBuff;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

public class Test {
    public static void main(String[] args) throws ParseException {
        MyByteBuff myByteBuff = new MyByteBuff();
        myByteBuff.addByte(((byte) 1));
        myByteBuff.addInt(2);
        myByteBuff.addDouble(3.3);
        myByteBuff.addChar('4');
        myByteBuff.addBoolean(true);
        myByteBuff.addString("555");
        System.out.println("==============================");
        System.out.println(myByteBuff.getByte());
        System.out.println("==============================");
        System.out.println(myByteBuff.getInt());
        System.out.println("==============================");
        System.out.println(myByteBuff.getDouble());
        System.out.println("==============================");
        System.out.println(myByteBuff.getChar());
        System.out.println("==============================");
        System.out.println(myByteBuff.getBoolean());
        System.out.println("==============================");
        System.out.println("下一个应该输出数据类型不对");
        myByteBuff.getBoolean();
        System.out.println("==============================");
        System.out.println(myByteBuff.getString());
        System.out.println("==============================");
        System.out.println("下一个应该输出数据查完了");
        myByteBuff.getString();
    }
}
