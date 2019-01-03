package com.liaoin.dataclean.entity;

import com.liaoin.dataclean.enums.BuildingIdentification;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @author mc
 * version 1.0v
 * date 2019/1/1 11:35
 * description TODO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "楼房")
@Entity(name = "f_building")
public class Building {
    @Id
    @GeneratedValue
    @ApiModelProperty("系统标识")
    private Long id ;


    @ApiModelProperty("城市")
    private String city;
    @ApiModelProperty("行政区")
    private String area;
	@Lob
    @ApiModelProperty("地址")
    private String address;
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("来源")
    private String sources;
	@Lob
    @ApiModelProperty("网站")
    private String website;
    @ApiModelProperty("建筑类型")
    private String type;
    @ApiModelProperty("开发商")
    private String developers;
    @ApiModelProperty("物业费")
    private String propertyFee;
    @ApiModelProperty("物管公司")
    private String managementCompany;
    @ApiModelProperty("绿化率")
    private String greenCoverage;
    @ApiModelProperty("容积率")
    private String cabbageCoverage;
    @Lob
    @ApiModelProperty("交通状况")
    private String traffic;
    @Lob
    @ApiModelProperty("医疗配套")
    private String medical;
    @Lob
    @ApiModelProperty("附近商业")
    private String nearbyBusiness;
    @ApiModelProperty("建筑面积")
    private String coveredArea;
    @ApiModelProperty("停车位")
    private String parkingLot;
    @ApiModelProperty("附近邮局")
    private String nearbyPost;
    @ApiModelProperty("占地面积")
    private String areaCovered;
    @ApiModelProperty("附近银行")
    private String nearbyBank;
    @ApiModelProperty("产权年限")
    private String ownershipTime;
    @Lob
    @ApiModelProperty("教育配套")
    private String educationMatching;
    @ApiModelProperty("产权类型")
    private String ownershipType;
    @ApiModelProperty("建筑年代")
    private String time;

    @ApiModelProperty("经度")
    private Double lon;
    @ApiModelProperty("纬度")
    private Double lat;
	@ApiModelProperty("标识")
	private BuildingIdentification identification;



}
