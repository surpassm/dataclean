package com.liaoin.dataclean.controller;

import com.liaoin.dataclean.service.FileUploadService;
import com.liaoin.dataclean.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @author mc
 * version 1.0v
 * date 2019/1/1 12:42
 * description 文件上传下载
 */
@CrossOrigin
@RestController
@RequestMapping("/building/")
@Api(tags  =  "文件API")
public class FileUploadController {

    @Resource
    private FileUploadService fileUploadService;
    @GetMapping("/downloadTemplate")
    @ApiOperation(value = "下载模板")
    public void downloadTemplate(HttpServletResponse response) {
        try {
            // 获取项目计划导入模板
            Workbook workbook = fileUploadService.getTemplate();
            // 响应文件下载
            response.setContentType("application/vnd.ms-excel");
            String filename = "楼盘信息模板.xls";
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/importTemplate")
    @ApiOperation(value = "导入楼盘数据")
    public Result importTemplate(@RequestParam MultipartFile file,
                                 @ApiParam(value = "经纬度相差距离单位：米 栗子：小于这个值会被删除",required = true) @RequestParam Integer distance,
                                 @ApiParam(value = "相似度 栗子：大于这个值会被删除",required = true)@RequestParam Integer degree) {
        try {
            return fileUploadService.importTemplate(file.getInputStream(),distance,degree);
        } catch (IOException e) {
            e.printStackTrace();
            return Result.fail("导入文件异常，无法完成解析");
        }
    }
}
