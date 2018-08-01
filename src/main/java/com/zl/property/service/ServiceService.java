package com.zl.property.service;

import com.zl.property.model.dto.FeeUser;
import com.zl.property.model.hib.UserInfo;
import com.zl.property.model.hib.property.Room;
import com.zl.property.model.hib.server.PropertyFee;
import com.zl.property.model.hib.server.Repair;
import com.zl.property.model.hib.utils.Banner;

import java.util.List;

public interface ServiceService {
    Repair getRepairsByRepairsId(Repair repair);
    List<Repair> getRepairsByUserIdAndState(Repair repair);
    Repair saveRepair(Repair repair);
    List<Banner> findBannerByVersion(Banner banner);
    Banner saveBanner(Banner banner);
    List<PropertyFee> getFeeByRoom(FeeUser feeUser);
    boolean addFeeByRoom(PropertyFee  propertyFee);
    boolean addRoom(Room room);
    List<Room> getRoomByUser(UserInfo userInfo);
}
