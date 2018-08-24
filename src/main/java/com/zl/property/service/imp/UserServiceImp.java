package com.zl.property.service.imp;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.OrderLogisticsInfo;
import com.zl.property.config.StateProperty;
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
            backUserInfo.setAesKey(StateProperty.AES_KEY);
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
                roomItem.setUserId(roomList.get(i).getUserId());
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


    public void alipay(){

    }

    @Override
    public boolean addRoomOnce() {
        for (int i = 1; i < 60; i++) {
            Building building = new Building();
            building.setBuildingName(i+"号楼");
            building.setMicrodistrictId("123123");
            building = buildingRepository.save(building);
            switch (i){
                case 38:
                case 51:
                case 52:
                    addUnit(i,building.getBuildingId(),1);
                    break;
                case 1:
                case 19:
                case 21:
                case 23:
                case 26:
                case 28:
                case 29:
                case 31:
                case 35:
                case 36:
                case 37:
                case 53:
                    addUnit(i,building.getBuildingId(),2);
                    break;
                case 2:
                case 3:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 13:
                case 15:
                case 16:
                case 17:
                case 18:
                case 20:
                case 22:
                case 25:
                case 27:
                case 30:
                case 32:
                case 33:
                case 55:
                case 58:
                case 59:
                    addUnit(i,building.getBuildingId(),3);
                    break;
                case 5:
                case 6:
                case 12:
                case 56:
                case 57:
                    addUnit(i,building.getBuildingId(),4);
                    break;
            }
        }
        return true;
    }

    public boolean addUnit(int building,String buildingId,int unitNum){
        for (int i = 1; i <= unitNum; i++) {
            Unit unit = new Unit();
            unit.setBuildingId(buildingId);
            unit.setUnitName(i+"单元");
            unit = unitRepository.save(unit);
            switch (building){
                case 1:
                case 2:
                    addRoom(buildingId,unit.getUnitId(),3,11,2);
                    break;
                case 3:
                    addRoom(buildingId,unit.getUnitId(),3,5,2);
                    break;
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                    addRoom(buildingId,unit.getUnitId(),1,5,2);
                    break;
                case 13:
                    addRoom(buildingId,unit.getUnitId(),1,6,2);
                    break;
                case 15:
                case 16:
                case 17:
                case 18:
                    addRoom(buildingId,unit.getUnitId(),1,11,2);
                    break;
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 25:
                case 26:
                case 27:
                case 30:
                    addRoom(buildingId,unit.getUnitId(),1,11,3);
                    break;
                case 28:
                case 29:
                    addRoom(buildingId,unit.getUnitId(),2,11,3);
                    break;
                case 31:
                case 32:
                case 33:
                case 35:
                    addRoom(buildingId,unit.getUnitId(),3,29,3);
                    break;
                case 37:
                case 38:
                    addRoom(buildingId,unit.getUnitId(),3,17,2);
                    break;
                case 39:
                case 50:
                case 53:
                    addRoom(buildingId,unit.getUnitId(),1,17,2);
                    break;
                case 51:
                case 52:
                    addRoom(buildingId,unit.getUnitId(),1,17,3);
                    break;
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                    addRoom(buildingId,unit.getUnitId(),1,2,2);
                    break;

            }
        }
        return true;
    }
    public boolean addRoom(String buildingId,String unitId,int startNum,int endNum,int num){
        for (int i = 1; i <= endNum-startNum; i++) {
            for (int j = 1; j <=num; j++) {
                Room room = new Room();
                room.setUnitId(unitId);
                room.setBuildingId(buildingId);
                String roomName = "";
                roomName = (startNum +i - 1)+"0"+j+"室";
                room.setRoomName(roomName);
                roomRepository.save(room);
            }

        }
        return true;
    }

}
