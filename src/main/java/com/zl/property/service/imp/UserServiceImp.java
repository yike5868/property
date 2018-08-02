package com.zl.property.service.imp;

import com.zl.property.model.dto.RoomItem;
import com.zl.property.model.hib.UserInfo;
import com.zl.property.model.hib.property.*;
import com.zl.property.repository.*;
import com.zl.property.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

    static Logger logger = LoggerFactory.getLogger(UserServiceImp.class);

    @Autowired
    UserInfoRepository userInfoRepository;
    @Autowired
    MicrodistrictRepository microdistrictRepository;
    @Autowired
    BuildingRepository buildingRepository;
    @Autowired
    UnitRepository unitRepository;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    VersionRepository versionRepository;


    public UserInfo findUserInfoByUserName(UserInfo userInfo) {

        UserInfo backUserInfo = userInfoRepository.findUserInfoByUserName(userInfo.getUserName());
        if (backUserInfo != null && backUserInfo.getPassword().equals(userInfo.getPassword())) {
            return backUserInfo;
        }
        return null;
    }

    @Override
    public UserInfo register(UserInfo userInfo) {
        UserInfo s = userInfoRepository.save(userInfo);
        List<Room> roomList = userInfo.getRoomList();
        if (roomList != null)
            for (int i = 0; i < roomList.size(); i++) {
                roomList.get(i).setUserId(s.getUserId());
            }
        roomRepository.saveAll(roomList);
        return s;
    }

    @Override
    public List<RoomItem> findAllMicrodistrict() {
        List<Microdistrict> microdistrictList = (List<Microdistrict>) microdistrictRepository.findAll();
        List<RoomItem> roomItemList = new ArrayList<>();
        if (microdistrictList != null) {
            for (int i = 0; i < microdistrictList.size(); i++) {
                RoomItem roomItem = new RoomItem();
                roomItem.setId(microdistrictList.get(i).getMicrodistrictId());
                roomItem.setName(microdistrictList.get(i).getMicrodistrictName());
                roomItem.setType("microdistrict");
                roomItemList.add(roomItem);
            }
        }
        return roomItemList;
    }

    @Override
    public List<RoomItem> findBuildingByMicrodistrictId(String microdistrict) {
        List<Building> buildingList = buildingRepository.findBuildingByMicrodistrictId(microdistrict);
        List<RoomItem> roomItemList = new ArrayList<>();
        if (buildingList != null) {
            for (int i = 0; i < buildingList.size(); i++) {
                RoomItem roomItem = new RoomItem();
                roomItem.setId(buildingList.get(i).getBuildingId());
                roomItem.setName(buildingList.get(i).getBuildingName());
                roomItem.setType("building");
                roomItemList.add(roomItem);
            }
        }
        return roomItemList;
    }

    @Override
    public List<RoomItem> findUnitByBuildingId(String building) {
        List<Unit> unitList = unitRepository.findUnitRoomByBuildingId(building);
        List<RoomItem> roomItemList = new ArrayList<>();
        if (unitList != null) {
            for (int i = 0; i < unitList.size(); i++) {
                RoomItem roomItem = new RoomItem();
                roomItem.setId(unitList.get(i).getUnitId());
                roomItem.setName(unitList.get(i).getUnitName());
                roomItem.setType("unit");
                roomItemList.add(roomItem);
            }
        }
        return roomItemList;
    }

    @Override
    public List<RoomItem> findRoomByUnitId(String unit) {
        List<Room> roomList = roomRepository.findRoomByUnitId(unit);
        List<RoomItem> roomItemList = new ArrayList<>();
        if (roomList != null) {
            for (int i = 0; i < roomList.size(); i++) {
                RoomItem roomItem = new RoomItem();
                roomItem.setId(roomList.get(i).getRoomId());
                roomItem.setName(roomList.get(i).getRoomName());
                roomItem.setType("room");
                roomItemList.add(roomItem);
            }
        }
        return roomItemList;
    }

    @Override
    public VersionDTO getAndroidVersion() {
       VersionDTO versionDTO = versionRepository.getVersionDTOByType("android");
        return versionDTO;
    }

}
