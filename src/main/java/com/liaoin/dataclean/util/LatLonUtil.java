package com.liaoin.dataclean.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author mc
 * @version V1.0
 * Title:LatLonUtil
 * Description:经纬度周边范围计算通用类
 * date 2017年8月31日下午3:10:42
 */
public class LatLonUtil {
	/**
	 * 圆周率
	 */
    private static final double PI = 3.14159265;
    /**
     * 地球半径
     */
    private static final double EARTH_RADIUS = 6378137;  
    /**
     * 计算半径
     */
    private static final double RAD = Math.PI / 180.0;
    /**
     * @param lat 纬度
	 * @param lon 经度
	 * @param radius 单位米(半径)
	 * @return double[minLat, minLng, maxLat, maxLng]
	 * Description:根据经纬度和半径计算经纬度范围
     */
    public static Map<String,Double> getAround(double lat, double lon, int radius) {
    	
	    Double latitude = lat;
	    Double longitude = lon;

	    Double degree = (24901 * 1609) / 360.0;

		Double dpmLat = 1 / degree;
	    Double radiusLat = dpmLat * (double) radius;
	    Double minLat = latitude - radiusLat;
	    Double maxLat = latitude + radiusLat;

	    Double mpdLng = degree * Math.cos(latitude * (PI / 180));
	    Double dpmLng = 1 / mpdLng;
	    Double radiusLng = dpmLng * (double) radius;
	    Double minLng = longitude - radiusLng;
	    Double maxLng = longitude + radiusLng;
	    Map<String,Double> result = new HashMap<>();
	    result.put("minLat", minLat);
	    result.put("minLng", minLng);
	    result.put("maxLat", maxLat);
	    result.put("maxLng", maxLng);
	    
	    return result;
	}
    /**
     * @param long1 第一个经度
	 * @param lat1 第一个纬度
	 * @param long2 第二经度
	 * @param lat2 第二纬度
	 * @return double
	 * Description:计算任意两点(经纬度)距离
     */
    public static double distanceByLongNLat(double long1, double lat1, double long2, double lat2) {
        double a, b;
        lat1 = lat1 * Math.PI / 180.0;
        lat2 = lat2 * Math.PI / 180.0;
        a = lat1 - lat2;
        b = (long1 - long2) * Math.PI / 180.0;
        double d;
        double sa2, sb2;
        sa2 = Math.sin(a / 2.0);
        sb2 = Math.sin(b / 2.0);
        d = 2 * EARTH_RADIUS * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1) * Math.cos(lat2) * sb2 * sb2));
        return d;
    }

	private static double rad(double d){
		return d * Math.PI / 180.0;
	}
	/**
	 * 计算两个经纬度之间的距离
	 * @param lat1 纬度1
	 * @param lng1 经度1
	 * @param lat2 纬度2
	 * @param lng2 经度2
	 * @return
	 */
	public static double GetDistance(double lat1, double lng1, double lat2, double lng2){

		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +
				Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
		//距离单位为：米
	 	double EARTH_RADIUS1 = 6371.393;
		s = s * EARTH_RADIUS1;
		s = Math.round(s * 1000);
		return s;
	}
}
