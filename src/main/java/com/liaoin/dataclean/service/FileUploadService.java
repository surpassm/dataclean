package com.liaoin.dataclean.service;


import com.liaoin.dataclean.common.Result;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.InputStream;

/**
 * @author mc
 * version 1.0v
 * date 2019/1/1 12:46
 * description TODO
 */
public interface FileUploadService {
    /**
     * 模板下载
     * @return Workbook
     */
    Workbook getTemplate();


    /**
     * 导入楼盘数据
     * @param inputStream 数据IO
     * @return Result
     */
    Result importTemplate(InputStream inputStream, Integer distance, Integer degree,Integer repeat);
}
