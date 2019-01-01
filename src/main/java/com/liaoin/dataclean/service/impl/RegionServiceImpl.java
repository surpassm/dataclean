package com.liaoin.dataclean.service.impl;

import com.liaoin.dataclean.entity.Region;
import com.liaoin.dataclean.repository.RegionRepository;
import com.liaoin.dataclean.service.RegionService;
import com.liaoin.dataclean.common.Result;
import com.liaoin.dataclean.common.Tips;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.*;

import static com.liaoin.dataclean.common.Result.fail;
import static com.liaoin.dataclean.common.Result.ok;

/**
  * @author mc
  * Create date 2019-01-01 12:34:18
  * Version 1.0
  * Description
  */
@Slf4j
@Service
@Transactional(rollbackFor={RuntimeException.class, Exception.class})
public class RegionServiceImpl implements RegionService {
    @Resource
    private RegionRepository regionRepository;

    @Override
    public Result insert(Region region) {
        if (region == null){
            return fail(Tips.PARAMETER_ERROR.msg);
        }
        regionRepository.save(region);
        return ok();
    }

    @Override
    public Result update(Region region) {
        if (region == null){
            return fail(Tips.PARAMETER_ERROR.msg);
        }
        regionRepository.save(region);
        return ok();
    }

    @Override
    public Result deleteInBatch(List<Region> regionList) {
        if (regionList.size() == 0){
            return fail(Tips.PARAMETER_ERROR.msg);
        }
        regionRepository.deleteInBatch(regionList);
        return ok();
    }

    @Override
    public Result deleteGetById(Long id){
        if (id == null){
            return fail(Tips.PARAMETER_ERROR.msg);
        }
        Optional<Region> optional = regionRepository.findById(id);
        if(!optional.isPresent()){
            return fail(Tips.MSG_NOT.msg);
        }
        regionRepository.deleteById(optional.get().getId());
        return ok();
    }


    @Override
    public Result findOne(Long id) {
        if (id == null){
        return fail(Tips.PARAMETER_ERROR.msg);
        }
        Region one = regionRepository.getOne(id);
        return ok(one);
    }

    @Override
    public Result pageQuery(Integer page, Integer size, String sort, Region region) {
        page = page == null ? 0 : page;
		size = size == null ? 10 : size;
    	if (page>0){page--;}
        PageRequest pageable = PageRequest.of(page, size);
        if (sort !=null && "".equals(sort.trim())) {
            pageable= PageRequest.of(page,page,new Sort(Sort.Direction.DESC,sort));
        }
        Page<Region> all = regionRepository.findAll((root,criteriaQuery,criteriaBuilder)-> {
            List<Predicate> list = new ArrayList<>();
            if (region != null) {
                if (region.getId() != null) {
                    list.add(criteriaBuilder.equal(root.get("id").as(Long.class), region.getId()));
                }
                if ((region.getName() != null) && !"".equals(region.getName().trim())) {
                    list.add(criteriaBuilder.like(root.get("name").as(String.class), "%" + region.getName() + "%"));
                }
            }
            return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
        }, pageable);
        Map<String, Object> map = new HashMap<>(16);
        map.put("total",all.getTotalElements());
        map.put("rows",all.getContent());
        return Result.ok(map);
    }
}

