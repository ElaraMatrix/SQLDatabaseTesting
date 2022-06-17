package utils;

import logger.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateFormatUtility {

    private DateFormatUtility() {}

    public static String getFormatDate(Date date) {
        Log.info("Get formatted time from: " + date.toString());
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }
}