package com.zl.property.repository;

import com.zl.property.model.hib.server.Repair;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ServiceRepository extends CrudRepository<Repair, Long> {
    Repair findRepairsByRepairsId(String roomId);
    List<Repair> findRepairsByUserId (String userId);
    List<Repair> findRepairsByState(String state);
    List<Repair> findRepairsByUserIdAndState(String userId, String state);
}
