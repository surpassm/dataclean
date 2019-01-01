package com.liaoin.dataclean.service.impl;

import com.liaoin.dataclean.entity.Building;
import com.liaoin.dataclean.repository.BuildingRepository;
import com.liaoin.dataclean.service.FileUploadService;
import com.liaoin.dataclean.common.Result;
import com.liaoin.dataclean.util.EditorDistanceUtil;
import com.liaoin.dataclean.util.LatLonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.liaoin.dataclean.common.Result.ok;

/**
 * @author mc
 * version 1.0v
 * date 2019/1/1 12:48
 * description TODO
 */
@Slf4j
@Service
public class FileUploadServiceImpl implements FileUploadService {
    @Resource
    private BuildingRepository buildingRepository;

    @Override
    public Workbook getTemplate() {
        // 创建工作簿
        Workbook workbook = new HSSFWorkbook();
        // 创建工作表
        Sheet sheet = workbook.createSheet("Sheet0");
        // 创建标题行
        CellStyle cellStyle = workbook.createCellStyle();
        // 设置背景色 等
        cellStyle.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.index);
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setFillBackgroundColor(IndexedColors.LIGHT_TURQUOISE.index);
        //水平居中
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        //垂直居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);

        Row headRow = sheet.createRow(0);
        Cell cell0 = headRow.createCell(0);
        cell0.setCellStyle(cellStyle);
        cell0.setCellValue("业务名称");
        Cell cell1 = headRow.createCell(1);
        cell1.setCellStyle(cellStyle);
        cell1.setCellValue("模块名称");
        Cell cell2 = headRow.createCell(2);
        cell2.setCellStyle(cellStyle);
        cell2.setCellValue("功能名称");
        Cell cell3 = headRow.createCell(3);
        cell3.setCellStyle(cellStyle);
        cell3.setCellValue("功能描述");
        Cell cell4 = headRow.createCell(4);
        cell4.setCellStyle(cellStyle);
        cell4.setCellValue("开始时间");
        Cell cell5 = headRow.createCell(5);
        cell5.setCellStyle(cellStyle);
        cell5.setCellValue("结束时间");
        Cell cell6 = headRow.createCell(6);
        cell6.setCellStyle(cellStyle);
        cell6.setCellValue("计划受领人");
        // 创建内容行
        Row contentRow = sheet.createRow(1);
        contentRow.createCell(0).setCellValue("填写业务相关");
        contentRow.createCell(1).setCellValue("填写模板相关");
        contentRow.createCell(2).setCellValue("填写功能相关");
        contentRow.createCell(3).setCellValue("填写功能描述");
        contentRow.createCell(4).setCellValue("日期格式：2018/10/1");
        contentRow.createCell(5).setCellValue("日期格式：2018/10/31");
        contentRow.createCell(6).setCellValue("任务受领人  如果多人采用中文顿号分隔(、)");
        sheet.setColumnWidth(0, 30 * 256);
        sheet.setColumnWidth(1, 30 * 256);
        sheet.setColumnWidth(2, 30 * 256);
        sheet.setColumnWidth(3, 30 * 256);
        sheet.setColumnWidth(4, 30 * 256);
        sheet.setColumnWidth(5, 30 * 256);
        sheet.setColumnWidth(6, 30 * 256);
        return workbook;
    }


    @Override
    public Result importTemplate(InputStream inputStream, Integer distance, Integer degree) {
        List<Building> buildings = new ArrayList<>();
        try {
            Workbook workbook = WorkbookFactory.create(inputStream);
            // 获取工作簿
            Sheet sheet = workbook.sheetIterator().next();
            for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
                Row row = sheet.getRow(i);
                if (row == null || i == 0) {
                    continue;
                }
                short lastCellNum = row.getLastCellNum();
                Building build = Building.builder().build();
                for (int j = 0; j < lastCellNum; j++) {
                    Cell cell = row.getCell(j);
                    if (cell == null) {
                        continue;
                    }
                    switch (j) {
                        case 0:
                            build.setCity(cell.getStringCellValue());
                            break;
                        case 1:
                            build.setArea(cell.getStringCellValue());
                            break;
                        case 2:
                            build.setAddress(cell.getStringCellValue());
                            break;
                        case 3:
                            build.setName(cell.getStringCellValue());
                            break;
                        case 4:
                            build.setSources(cell.getStringCellValue());
                            break;
                        case 5:
                            build.setWebsite(cell.getStringCellValue());
                            break;
                        case 6:
                            build.setType(cell.getStringCellValue());
                            break;
                        case 7:
                            String[] split = cell.getStringCellValue().split(",");
                            if (split.length == 2) {
                                build.setLon(Double.valueOf(split[0]));
                                build.setLat(Double.valueOf(split[1]));
                            }
                            break;
                        case 8:
                            build.setTime(cell.getStringCellValue());
                            break;
                        case 9:
                            build.setDevelopers(cell.getStringCellValue());
                            break;
                        case 10:
                            build.setPropertyFee(cell.getStringCellValue());
                            break;
                        case 11:
                            build.setManagementCompany(cell.getStringCellValue());
                            break;
                        case 12:
                            build.setGreenCoverage(cell.getStringCellValue());
                            break;
                        case 13:
                            build.setCabbageCoverage(cell.getStringCellValue());
                            break;
                        case 14:
                            build.setTraffic(cell.getStringCellValue());
                            break;
                        case 15:
                            build.setMedical(cell.getStringCellValue());
                            break;
                        case 16:
                            build.setNearbyBusiness(cell.getStringCellValue());
                            break;
                        case 17:
                            build.setCoveredArea(cell.getStringCellValue());
                            break;
                        case 18:
                            build.setParkingLot(cell.getStringCellValue());
                            break;
                        case 19:
                            build.setNearbyPost(cell.getStringCellValue());
                            break;
                        case 20:
                            build.setAreaCovered(cell.getStringCellValue());
                            break;
                        case 21:
                            build.setNearbyBank(cell.getStringCellValue());
                            break;
                        case 22:
                            build.setOwnershipTime(cell.getStringCellValue());
                            break;
                        case 23:
                            build.setEducationMatching(cell.getStringCellValue());
                            break;
                        case 24:
                            build.setOwnershipType(cell.getStringCellValue());
                            break;
                        default:
                            break;

                    }

                }
                buildings.add(build);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //楼盘名称相似度控制
        for (int i = 0; i < buildings.size() - 1; i++) {
            for (int j = buildings.size() - 1; j > i; j--) {
                if (buildings.get(i).getName() == null || buildings.get(j).getName()==null){
                    continue;
                }
                float similarityRatio = EditorDistanceUtil.getSimilarityRatio(buildings.get(i).getName(), buildings.get(j).getName()) * 100;
                if (degree < similarityRatio) {
                    buildings.remove(j);
                }
            }
        }
        //楼盘距离控制
        for (int i = 0; i < buildings.size() - 1; i++) {
            for (int j = buildings.size() - 1; j > i; j--) {
                if (buildings.get(i).getLon() == null || buildings.get(i).getLat() == null){
                    continue;
                }
                if (buildings.get(j).getLon() == null || buildings.get(j).getLat() == null){
                    continue;
                }
                double longNLat = LatLonUtil.distanceByLongNLat(buildings.get(i).getLon(), buildings.get(i).getLat(), buildings.get(j).getLon(), buildings.get(j).getLat());
                if (distance > longNLat) {
                    buildings.remove(j);
                }
            }
        }
        //最终结果
        buildingRepository.saveAll(buildings);
        return ok();
    }
}
