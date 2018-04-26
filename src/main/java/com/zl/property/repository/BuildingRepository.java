package com.zl.property.repository;

import com.zl.property.model.hib.property.Building;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BuildingRepository extends CrudRepository<Building, Long> {
    List<Building> findBuildingByMicrodistrictId (String microdistrictId);
}
