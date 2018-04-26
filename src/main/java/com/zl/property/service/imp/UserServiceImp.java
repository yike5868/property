package com.zl.property.service.imp;

import com.zl.property.model.hib.UserInfo;
import com.zl.property.model.hib.property.Building;
import com.zl.property.model.hib.property.Microdistrict;
import com.zl.property.model.hib.property.Room;
import com.zl.property.model.hib.property.Unit;
import com.zl.property.repository.*;
import com.zl.property.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    static Logger logger = LoggerFactory.getLogger(UserServiceImp.class);

    @Autowired
    UserInfoRepository userInfoRepository ;
    @Autowired
    MicrodistrictRepository microdistrictRepository;
    @Autowired
    BuildingRepository buildingRepository;
    @Autowired
    UnitRepository unitRepository;
    @Autowired
    RoomRepository roomRepository;


    public UserInfo findUserInfoByUserName(UserInfo userInfo){

        UserInfo backUserInfo = userInfoRepository.findUserInfoByUserName(userInfo.getUserName());
        if(backUserInfo!=null && backUserInfo.getPassword().equals(userInfo.getPassword())){
            return backUserInfo;
        }
        return null;
    }

    @Override
    public UserInfo register(UserInfo userInfo) {
        UserInfo s = userInfoRepository.save(userInfo);
        return s;
    }

    @Override
    public List<Microdistrict> findAllMicrodistrict() {
        List<Microdistrict> microdistrictList = (List<Microdistrict>) microdistrictRepository.findAll();
        return microdistrictList;
    }

    @Override
    public List<Building> findBuildingByMicrodistrictId(Microdistrict microdistrict) {
        List<Building> buildingList = buildingRepository.findBuildingByMicrodistrictId(microdistrict.getMicrodistrictId());
        return buildingList;
    }

    @Override
    public List<Unit> findUnitByBuildingId(Building building) {
        List<Unit> unitList = unitRepository.findUnitRoomByBuildingId(building.getBuildingId());
        return unitList;
    }

    @Override
    public List<Room> findRoomByUnitId(Unit unit) {
        List<Room> roomList = roomRepository.findRoomByUnitId(unit.getUnitId());
        return roomList;
    }

}
