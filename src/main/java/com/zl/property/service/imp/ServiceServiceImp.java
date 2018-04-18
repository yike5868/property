package com.zl.property.service.imp;

import com.zl.property.model.hib.UserInfo;
import com.zl.property.model.hib.server.Repair;
import com.zl.property.repository.ServiceRepository;
import com.zl.property.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceServiceImp implements ServiceService {

    @Autowired
    ServiceRepository serviceRepository;

    @Override
    public Repair getRepairsByRoomId(Repair repair) {
        Repair repairs = serviceRepository.findRepairsByRoomId(repair.getRoomId());
        return repairs;
    }

    @Override
    public List<Repair> getRepairsByUserId(Repair repair) {
        List<Repair> repairs = serviceRepository.findRepairsByUserId(repair.getUserId());
        return repairs;
    }
    @Override
    public List<Repair> getRepairsByUserIdAndState(Repair repair) {
        List<Repair> repairs = serviceRepository.findRepairsByUserIdAndState(repair.getRoomId(),repair.getState());
        return repairs;
    }

    @Override
    public Repair saveRepair(Repair repair) {
        Repair repair1 = serviceRepository.save(repair);
        return repair1;
    }


}
