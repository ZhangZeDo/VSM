package com.zzd.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UniqueIdUtil {

    public static String buildId(String preStr){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String time = sdf.format(new Date(System.currentTimeMillis()));
        String userId = preStr+time;
        return userId;
    }
}
