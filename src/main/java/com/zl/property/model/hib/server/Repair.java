package com.zl.property.model.hib.server;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.annotation.Configuration;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import javax.persistence.Transient;

@Configuration
@Entity
@Table(name = "p_server_repairs")
public class Repair  {
    @Id
    @GeneratedValue(generator="system_uuid")
    @GenericGenerator(name="system_uuid",strategy="uuid")
    @Column(name = "repairs_id", unique = true, nullable = false)
    private String repairsId;
    /**
     * 房间id
     */
    @Column(name = "room_id")
    private String roomId;
    /**
     * 用户id
     */
    @Column(name = "user_id")
    private String userId;
    /**
     * 信息
     */
    @Column(name = "message")
    private String message;
    /**
     * 方便维修时间
     */
    @Column(name = "begin_time")
    private Date beginTime;
    /**
     * 方便维修结束时间
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 方便维修日期
     */
    @Column(name = "begin_date")
    private Date beginDate;
    @Column(name = "end_date")
    private Date endDate;
    /**
     * 图片列表
     */
    @Column(name = "photos")
    private String photos;

    @OneToMany( cascade = {CascadeType.ALL} ,fetch=FetchType.EAGER )
    private List<Photo> PhotoList;

    /**
     * 状态
     */
    @Column(name = "state")
    private String state;
    /**
     * 工作人
     */
    @Column(name = "workerId")
    private String workerId;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;
    /**
     * 评价
     */
    @Column(name = "evaluate")
    private String evaluate;

    /**
     * 星级
     */

    @Column(name = "stars")
    private String stars;

    /**
     *  服务类型
     */
    @Column(name = "type")
    private String type;

    /**

     */
    @Transient
    private int pageSize;
    @Transient
    private int pageIndex;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<Photo> getPhotoList() {
        return PhotoList;
    }

    public void setPhotoList(List<Photo> photoList) {
        PhotoList = photoList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRepairsId() {
        return repairsId;
    }

    public void setRepairsId(String repairsId) {
        this.repairsId = repairsId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getWorkerId() {
        return workerId;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }
}
