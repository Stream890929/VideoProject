package utils;

import org.springframework.util.DigestUtils;

/**
 * @author Stream
 * @version 1.0
 * @date 2019/08/26 16:43
 */
public class Md5Utils {
    public static String getMd5Str(String mingWen) {
        String firstMi = DigestUtils.md5DigestAsHex (mingWen.getBytes ());
        String jieStr = firstMi.substring (5, 16);
        String salt = "Stream";
        String newStr = firstMi + jieStr + salt;
        return DigestUtils.md5DigestAsHex (newStr.getBytes ());
    }

    public static void main(String[] args) {
        System.out.println (getMd5Str ("123456"));
    }

}
