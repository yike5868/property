package com.zl.property.model.hib.property;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 单元和房间
 */
@Entity
@Table(name = "p_unit_room")
public class UnitRoom {
    @Id
    @GeneratedValue(generator="system_uuid")
    @GenericGenerator(name="system_uuid",strategy="uuid")
    @Column(name = "room_id", unique = true, nullable = false)
    private String roomId;

    @Column(name = "building_id")
    private String buildingId;
    @Column(name = "unit_id")
    private String unitId;
    @Column(name = "unit_name")
    private String unitName;

    @Column(name = "room_name")
    private String roomName;

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
