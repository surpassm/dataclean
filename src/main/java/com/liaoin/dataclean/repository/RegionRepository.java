package com.liaoin.dataclean.repository;

import com.liaoin.dataclean.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
  * @author mc
  * Create date 2019-01-01 12:34:18
  * Version 1.0
  * Description
  */
public interface RegionRepository extends JpaRepository<Region, Long>, JpaSpecificationExecutor<Region> {

}