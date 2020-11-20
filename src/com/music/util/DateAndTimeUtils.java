package com.music.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAndTimeUtils {
	/**
	 * 时间yyyy-MM-dd HH:mm:ss
	 */
	public static String getTime(){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpleDateFormat.format(new Date());
	}
}
