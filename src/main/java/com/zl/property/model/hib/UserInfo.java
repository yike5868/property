package com.zl.property.model.hib;

import com.zl.property.model.hib.property.Room;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.annotation.Configuration;

import javax.persistence.*;
import java.util.List;

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
    @Column(name = "head_path")
    private String headPath;
    @Transient
    private List<Room> roomList;

    @Transient
    private String aesKey;

    @Transient
    public List<Room> getRoomList() {
        return roomList;
    }
    @Transient
    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }
    @Transient
    public String getAesKey() {
        return aesKey;
    }
    @Transient
    public void setAesKey(String aesKey) {
        this.aesKey = aesKey;
    }

    public String getHeadPath() {
        return headPath;
    }

    public void setHeadPath(String headPath) {
        this.headPath = headPath;
    }

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

}
