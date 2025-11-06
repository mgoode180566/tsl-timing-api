package com.mgoode.tsl_timing_api.event.utils;

public class GPSConversionUtil {
	
	public static double convertDdMmToDecimal(double ddmm, boolean isNegative) {
		double degrees = Math.floor(ddmm / 100);
		double minutes = ddmm - (degrees * 100);
		double decimal = degrees + (minutes / 60.0);
		return isNegative ? -decimal : decimal;
	}
}
