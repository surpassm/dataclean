package com.liaoin.dataclean.service;

import com.liaoin.dataclean.entity.Region;
import com.liaoin.dataclean.common.Result;

import java.util.List;

/**
  * @author mc
  * Create date 2019-01-01 12:34:18
  * Version 1.0
  * Description
  */
public interface RegionService {
    /**
	 * 新增
	 * @param region 对象
	 * @return 前端返回格式
	 */
    Result insert(Region region);
    /**
	 * 修改
	 * @param region 对象
	 * @return 前端返回格式
	 */
    Result update(Region region);
    /**
	 * 批量删除
	 * @param regionList 对象
	 * @return 前端返回格式
	 */
    Result deleteInBatch(List<Region> regionList);
    /**
	 * 根据主键删除
	 * @param id 标识
	 * @return 前端返回格式
	 */
    Result deleteGetById(Long id);
    /**
	 * 根据主键查询
	 * @param id 标识
	 * @return 前端返回格式
	 */
    Result findOne(Long id);
    /**
	 * 条件分页查询
	 * @param page 当前页
	 * @param size 显示多少条
	 * @param sort 排序字段
	 * @param region 查询条件
	 * @return 前端返回格式
	 */
    Result pageQuery(Integer page, Integer size, String sort, Region region);
}
