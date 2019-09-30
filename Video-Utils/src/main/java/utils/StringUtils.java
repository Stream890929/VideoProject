package utils;

import java.text.DecimalFormat;

/**
 * string 转 时间格式
 * @author Stream
 */
public class StringUtils {
    public static String getStrTime(int time){
        StringBuilder sb =new StringBuilder();
        int hour = time / 3600;
        DecimalFormat df = new DecimalFormat("00");
        sb.append(df.format(hour)+":");

        int temp = time % 3600;
        int minute = temp / 60 ;
        sb.append(df.format(minute)+":");

        int second = temp % 60;
        sb.append(df.format(second));

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(getStrTime(7260));
    }
}
