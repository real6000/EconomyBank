package org.brokeski.economyBank.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

    public static String formatTime(long millis){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date(millis));
    }
}
