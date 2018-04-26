package com.zl.property.service;

import com.zl.property.model.hib.UserInfo;
import com.zl.property.model.hib.property.Building;
import com.zl.property.model.hib.property.Microdistrict;
import com.zl.property.model.hib.property.Room;
import com.zl.property.model.hib.property.Unit;

import java.util.List;

public interface UserService {
    UserInfo findUserInfoByUserName(UserInfo userInfo);
    UserInfo register(UserInfo userInfo);

    List<Microdistrict> findAllMicrodistrict();
    List<Building> findBuildingByMicrodistrictId(Microdistrict microdistrict);
    List<Unit> findUnitByBuildingId(Building building);
    List<Room> findRoomByUnitId(Unit unit);
}
