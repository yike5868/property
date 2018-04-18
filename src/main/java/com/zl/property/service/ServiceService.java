package com.zl.property.service;

import com.zl.property.model.hib.UserInfo;
import com.zl.property.model.hib.server.Repair;

import java.util.List;

public interface ServiceService {
    Repair getRepairsByRoomId(Repair repair);
    List<Repair> getRepairsByUserId(Repair repair);
    List<Repair> getRepairsByUserIdAndState(Repair repair);
    Repair saveRepair(Repair repair);
}
