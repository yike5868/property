package com.zl.property.model.hib.server;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.annotation.Configuration;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Configuration
@Entity
@Table(name = "p_server_property_fee")
public class PropertyFee {
    @Id
    @GeneratedValue(generator="system_uuid")
    @GenericGenerator(name="system_uuid",strategy="uuid")
    @Column(name = "fee_id", unique = true, nullable = false)
    private String id;
    /**
     * 物业费时间
     */
    @Column(name = "begin_date")
    private Date beginDate;

    @Column(name = "end_date")
    private Date endDate;
    /**
     * 缴费 名称
     */

    @Column(name="pay_name")
    private String payName;
    /**
     * 缴费时间
     */
    @Column(name = "pay_date")
    private Date payDate;

    /**
     * 缴费金额
     */
    @Column(name = "pay_money")
    private BigDecimal payMoney;

    /**
     * 房间id
     */
    @Column(name = "room_id")
    private String roomId;
    /**
     * 缴费人id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 支付状态
     */
    @Column(name = "pay_state")
    private String payState;

    /**
     * 付钱类型
     */
    @Column(name = "pay_type")
    private String payType;

    /**
     * 缴费人
     */
    @Column(name = "user_name")
    private String userName;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public BigDecimal getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(BigDecimal payMoney) {
        this.payMoney = payMoney;
    }

    public String getPayState() {
        return payState;
    }

    public void setPayState(String payState) {
        this.payState = payState;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }
}
