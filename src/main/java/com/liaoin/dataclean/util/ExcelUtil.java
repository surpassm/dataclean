package com.liaoin.dataclean.util;

/**
 * @author mc
 * version 1.0v
 * date 2019/1/1 14:31
 * description TODO
 */
public class ExcelUtil {
    /**
     * 根据后缀判断版本
     */
    public static boolean isExcel(String pathname) {
        if (pathname == null) {
            return false;
        }
        return pathname.endsWith(".xls") || pathname.endsWith(".xlsx");
    }
}
