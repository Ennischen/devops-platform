package com.panda.devops.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String format2String(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (date == null) {
            return "";
        }
        return df.format(date);
    }
}
