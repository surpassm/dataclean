package com.liaoin.dataclean.enums;

import lombok.Getter;

/**
 * @author mc
 * Create date 2019/1/3 10:38
 * Version 1.0
 * Description 楼盘标识枚举
 */
@Getter
public enum BuildingIdentification {
	/**
	 *保留数据
	 */
	RETAIN_DATA(1,"保留数据"),
	/**
	 * 重复数据
	 */
	REPEAT_DATA(0,"重复数据"),

	;

	private Integer key;

	private String name;

	BuildingIdentification(Integer key,String name){
		this.key = key;
		this.name = name;
	}
}
