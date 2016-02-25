package com.weibo.dip.htk.func;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by fpschina on 16/2/18.
 */
public class GetTime {
    public static String getTimeToSec() {
        Date date = new Date();
        //二十四小时制
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYY-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    public static long getTimeToms() {
        Date date = new Date();
        return date.getTime();
    }

}
