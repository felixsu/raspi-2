package com.felix.raspi.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by fsoewito on 4/13/2016.
 */
public class DateUtil {
    public static SimpleDateFormat COMMON_DATE_FORMATTER = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss a z ");

    public static String dateAsString(DateFormat df, long d){
        if (df.equals(DateFormat.COMMON)){
            return COMMON_DATE_FORMATTER.format(new Date(d));
        }else {
            return COMMON_DATE_FORMATTER.format(new Date(d));
        }
    }

    public static String dateAsString(DateFormat df){
        return dateAsString(df, new Date().getTime());
    }

    public enum DateFormat{
        COMMON,
        ISO8583
    }
}
