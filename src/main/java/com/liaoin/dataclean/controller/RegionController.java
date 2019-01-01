package com.liaoin.dataclean.controller;

import com.liaoin.dataclean.entity.Region;
import com.liaoin.dataclean.service.RegionService;
import com.liaoin.dataclean.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
  * @author mc
  * Create date 2019-01-01 12:34:18
  * Version 1.0
  * Description
  */
@CrossOrigin
@RestController
@RequestMapping("/region/")
@Api(tags  =  "区域API")
public class RegionController {

    @Resource
    private RegionService regionService;

    @PostMapping("insert")
    @ApiOperation(value = "新增")
    public Result save(@ApiParam(hidden = true) Integer userId,
                       @RequestBody Region region,
                       BindingResult errors) {
        return regionService.insert(region);
    }

    @PostMapping("update")
    @ApiOperation(value = "修改")
    public Result update(@ApiParam(hidden = true) Integer userId,
                         @RequestBody Region region,
                         BindingResult errors) {
        return regionService.update(region);
    }

    @PostMapping("deleteInBatch")
    @ApiOperation(value = "批量删除")
    public Result deleteInBatch(@ApiParam(hidden = true) Integer userId,
                                @RequestBody List<Region> regionList) {
        return regionService.deleteInBatch(regionList);
    }

    @PostMapping("getById")
    @ApiOperation(value = "根据主键删除")
    public Result deleteGetById(@ApiParam(hidden = true) Integer userId,
                                @ApiParam(value = "主键",required = true)@RequestParam(value = "id") Long id) {
        return regionService.deleteGetById(id);
    }

    @GetMapping("findOne")
    @ApiOperation(value = "根据主键查询")
    public Result findOne(@ApiParam(hidden = true) Integer userId,
                          @ApiParam(value = "主键",required = true)@RequestParam(value = "id") Long id) {
        return regionService.findOne(id);
    }

    @PostMapping("pageQuery")
    @ApiOperation(value = "条件分页查询")
    public Result pageQuery(@ApiParam(hidden = true) Integer userId,
                            @ApiParam(value = "第几页", required = true) @RequestParam(value = "page") Integer page,
                            @ApiParam(value = "多少条",required = true)@RequestParam(value = "size") Integer size,
                            @ApiParam(value = "排序字段")@RequestParam(value = "sort",required = false) String sort,
                            @RequestBody(required = false) Region region) {
        return regionService.pageQuery(page, size, sort, region);
    }
}
