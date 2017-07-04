package com.dawn.styledawn;

/**
 * Created by 90449 on 2017/7/4.
 */

public class StringUtil {
    public static boolean isEmpty(String str){
        if(str == null || str.trim().length() == 0)
            return true;
        return false;
    }
}
