package com.zl.property.model.hib;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.annotation.Configuration;

import javax.persistence.*;

@Configuration
@Entity
@Table(name = "p_user_info")
public class UserInfo{
    @Id
    @GeneratedValue(generator="system_uuid")
    @GenericGenerator(name="system_uuid",strategy="uuid")
    @Column(name = "user_id", unique = true, nullable = false)
    private String userId;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "user_roles")
    private String userRoles;//角色
    @Column(name = "real_name")
    private String realName;
    @Column(name = "phone")
    private String phone;
    @Column(name = "address")
    private String address;
    @Column(name = "microdistrict_id")
    private String microdistrictId;//楼栋
    @Column(name = "building_id")
    private String buildingId;
    @Column(name = "unit_id")
    private String unitId;
    @Column(name = "room_id")
    private String roomId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(String userRoles) {
        this.userRoles = userRoles;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMicrodistrictId() {
        return microdistrictId;
    }

    public void setMicrodistrictId(String microdistrictId) {
        this.microdistrictId = microdistrictId;
    }

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

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}
