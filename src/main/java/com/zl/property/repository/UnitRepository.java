package com.zl.property.repository;

import com.zl.property.model.hib.property.Unit;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UnitRepository extends CrudRepository<Unit, Long> {
    List<Unit> findUnitRoomByBuildingId (String buildingId);
}
