package com.liaoin.dataclean.service.impl;

import com.liaoin.dataclean.entity.Building;
import com.liaoin.dataclean.repository.BuildingRepository;
import com.liaoin.dataclean.service.BuildingService;
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
public class BuildingServiceImpl implements BuildingService {
    @Resource
    private BuildingRepository buildingRepository;

    @Override
    public Result insert(Building building) {
        if (building == null){
            return fail(Tips.PARAMETER_ERROR.msg);
        }
        buildingRepository.save(building);
        return ok();
    }

    @Override
    public Result update(Building building) {
        if (building == null){
            return fail(Tips.PARAMETER_ERROR.msg);
        }
        buildingRepository.save(building);
        return ok();
    }

    @Override
    public Result deleteInBatch(List<Building> buildingList) {
        if (buildingList.size() == 0){
            return fail(Tips.PARAMETER_ERROR.msg);
        }
        buildingRepository.deleteInBatch(buildingList);
        return ok();
    }

    @Override
    public Result deleteGetById(Long id){
        if (id == null){
            return fail(Tips.PARAMETER_ERROR.msg);
        }
        Optional<Building> optional = buildingRepository.findById(id);
        if(!optional.isPresent()){
            return fail(Tips.MSG_NOT.msg);
        }
        buildingRepository.deleteById(optional.get().getId());
        return ok();
    }


    @Override
    public Result findOne(Long id) {
        if (id == null){
        return fail(Tips.PARAMETER_ERROR.msg);
        }
        Building one = buildingRepository.getOne(id);
        return ok(one);
    }

    @Override
    public Result pageQuery(Integer page, Integer size, String sort, Building building) {
        page = page == null ? 0 : page;
		size = size == null ? 10 : size;
    	if (page>0){page--;}
        PageRequest pageable = PageRequest.of(page, size);
        if (sort !=null && "".equals(sort.trim())) {
            pageable= PageRequest.of(page,page,new Sort(Sort.Direction.DESC,sort));
        }
        Page<Building> all = buildingRepository.findAll((root,criteriaQuery,criteriaBuilder)-> {
            List<Predicate> list = new ArrayList<>();
            if (building != null) {
                if (building.getId() != null) {
                    list.add(criteriaBuilder.equal(root.get("id").as(Long.class), building.getId()));
                }
                if ((building.getAddress() != null) && !"".equals(building.getAddress().trim())) {
                    list.add(criteriaBuilder.like(root.get("address").as(String.class), "%" + building.getAddress() + "%"));
                }
                if ((building.getArea() != null) && !"".equals(building.getArea().trim())) {
                    list.add(criteriaBuilder.like(root.get("area").as(String.class), "%" + building.getArea() + "%"));
                }
                if ((building.getAreaCovered() != null) && !"".equals(building.getAreaCovered().trim())) {
                    list.add(criteriaBuilder.like(root.get("areaCovered").as(String.class), "%" + building.getAreaCovered() + "%"));
                }
                if ((building.getCabbageCoverage() != null) && !"".equals(building.getCabbageCoverage().trim())) {
                    list.add(criteriaBuilder.like(root.get("cabbageCoverage").as(String.class), "%" + building.getCabbageCoverage() + "%"));
                }
                if ((building.getDevelopers() != null) && !"".equals(building.getDevelopers().trim())) {
                    list.add(criteriaBuilder.like(root.get("developers").as(String.class), "%" + building.getDevelopers() + "%"));
                }
                if ((building.getEducationMatching() != null) && !"".equals(building.getEducationMatching().trim())) {
                    list.add(criteriaBuilder.like(root.get("educationMatching").as(String.class), "%" + building.getEducationMatching() + "%"));
                }
                if ((building.getGreenCoverage() != null) && !"".equals(building.getGreenCoverage().trim())) {
                    list.add(criteriaBuilder.like(root.get("greenCoverage").as(String.class), "%" + building.getGreenCoverage() + "%"));
                }
                if (building.getLat() != null) {
                    list.add(criteriaBuilder.equal(root.get("lat").as(Double.class), building.getLat()));
                }
                if (building.getLon() != null) {
                    list.add(criteriaBuilder.equal(root.get("lon").as(Double.class), building.getLon()));
                }
                if ((building.getManagementCompany() != null) && !"".equals(building.getManagementCompany().trim())) {
                    list.add(criteriaBuilder.like(root.get("managementCompany").as(String.class), "%" + building.getManagementCompany() + "%"));
                }
                if ((building.getMedical() != null) && !"".equals(building.getMedical().trim())) {
                    list.add(criteriaBuilder.like(root.get("medical").as(String.class), "%" + building.getMedical() + "%"));
                }
                if ((building.getName() != null) && !"".equals(building.getName().trim())) {
                    list.add(criteriaBuilder.like(root.get("name").as(String.class), "%" + building.getName() + "%"));
                }
                if ((building.getNearbyBank() != null) && !"".equals(building.getNearbyBank().trim())) {
                    list.add(criteriaBuilder.like(root.get("nearbyBank").as(String.class), "%" + building.getNearbyBank() + "%"));
                }
                if ((building.getNearbyBusiness() != null) && !"".equals(building.getNearbyBusiness().trim())) {
                    list.add(criteriaBuilder.like(root.get("nearbyBusiness").as(String.class), "%" + building.getNearbyBusiness() + "%"));
                }
                if ((building.getNearbyPost() != null) && !"".equals(building.getNearbyPost().trim())) {
                    list.add(criteriaBuilder.like(root.get("nearbyPost").as(String.class), "%" + building.getNearbyPost() + "%"));
                }
                if ((building.getOwnershipTime() != null) && !"".equals(building.getOwnershipTime().trim())) {
                    list.add(criteriaBuilder.like(root.get("ownershipTime").as(String.class), "%" + building.getOwnershipTime() + "%"));
                }
                if ((building.getOwnershipType() != null) && !"".equals(building.getOwnershipType().trim())) {
                    list.add(criteriaBuilder.like(root.get("ownershipType").as(String.class), "%" + building.getOwnershipType() + "%"));
                }
                if ((building.getParkingLot() != null) && !"".equals(building.getParkingLot().trim())) {
                    list.add(criteriaBuilder.like(root.get("parkingLot").as(String.class), "%" + building.getParkingLot() + "%"));
                }
                if ((building.getPropertyFee() != null) && !"".equals(building.getPropertyFee().trim())) {
                    list.add(criteriaBuilder.like(root.get("propertyFee").as(String.class), "%" + building.getPropertyFee() + "%"));
                }
                if ((building.getSources() != null) && !"".equals(building.getSources().trim())) {
                    list.add(criteriaBuilder.like(root.get("sources").as(String.class), "%" + building.getSources() + "%"));
                }
                if (building.getTime() != null) {
                    list.add(criteriaBuilder.equal(root.get("time").as(Date.class), building.getTime()));
                }
                if ((building.getTraffic() != null) && !"".equals(building.getTraffic().trim())) {
                    list.add(criteriaBuilder.like(root.get("traffic").as(String.class), "%" + building.getTraffic() + "%"));
                }
                if ((building.getType() != null) && !"".equals(building.getType().trim())) {
                    list.add(criteriaBuilder.like(root.get("type").as(String.class), "%" + building.getType() + "%"));
                }
                if ((building.getWebsite() != null) && !"".equals(building.getWebsite().trim())) {
                    list.add(criteriaBuilder.like(root.get("website").as(String.class), "%" + building.getWebsite() + "%"));
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

