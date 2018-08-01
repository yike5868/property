package com.zl.property.service.imp;

import com.zl.property.config.StateProperty;
import com.zl.property.model.dto.FeeUser;
import com.zl.property.model.hib.UserInfo;
import com.zl.property.model.hib.property.Room;
import com.zl.property.model.hib.server.Photo;
import com.zl.property.model.hib.server.PropertyFee;
import com.zl.property.model.hib.server.Repair;
import com.zl.property.model.hib.utils.Banner;
import com.zl.property.repository.*;
import com.zl.property.service.ServiceService;
import com.zl.property.utils.EveryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.rmi.PortableRemoteObject;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ServiceServiceImp implements ServiceService{

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    PhotoRepository photoRepository;


    @Autowired
    BannerRepository bannerRepository;

    @Autowired
    PropertyFeeRepository propertyFeeRepository;

    @Autowired
    RoomRepository roomRepository;


    /**
     * 根据id获取服务订单
     *
     * @param repair
     * @return
     */
    @Override
    public Repair getRepairsByRepairsId(Repair repair) {
        Repair repairs = serviceRepository.findRepairsByRepairsId(repair.getRepairsId());
        return repairs;
    }

    /**
     * 根据用户名和状态获取服务订单列表
     *
     * @param repair
     * @return
     */
    @Override
    public List<Repair> getRepairsByUserIdAndState(Repair repair) {
        List<Repair> repairs;
        if (repair.getState() == null)
            repairs = serviceRepository.findRepairsByUserId(repair.getUserId());
        else
            repairs = serviceRepository.findRepairsByUserIdAndState(repair.getRoomId(), repair.getState());

        return repairs;
    }


    /**
     * 保存订单
     *
     * @param repair
     * @return
     */
    @Override
    public Repair saveRepair(Repair repair) {
        Repair repair1 = serviceRepository.save(repair);
        return repair1;
    }

    /**
     * 获取banner 图片
     */
    @Override
    public List<Banner> findBannerByVersion(Banner banner) {
        List<Banner> bannerList = bannerRepository.findBannerByVersion(banner.getVersion());

        return bannerList;
    }

    @Override
    public Banner saveBanner(Banner banner) {
        banner = bannerRepository.save(banner);
        return banner;
    }

    /**
     * 根据房间获取需要缴费的金额
     * @param feeUser
     * @return
     */
    @Override
    public List<PropertyFee> getFeeByRoom(FeeUser feeUser) {
        if (feeUser == null) {
            return null;
        }
        List<PropertyFee> propertyFeeList = propertyFeeRepository.findPropertyFee(feeUser.getRoomId(), feeUser.getPayState(), (feeUser.getPageIndex()-1)*feeUser.getPageSize(), feeUser.getPageSize());

        if(EveryUtils.isEmpty(propertyFeeList)&&StateProperty.PAY_NO.equals(feeUser.getPayState())){
            propertyFeeList = propertyFeeRepository.findPropertyFee(feeUser.getRoomId(), StateProperty.PAY_YES, (feeUser.getPageIndex()-1)*feeUser.getPageSize(), feeUser.getPageSize());
            if(EveryUtils.isEmpty(propertyFeeList)){
                return null;
            }
            PropertyFee oldFee = propertyFeeList.get(0);
            PropertyFee propertyFee = new PropertyFee();
            propertyFee.setUserId(feeUser.getUserId());
            propertyFee.setRoomId(feeUser.getRoomId());
            propertyFee.setPayName(EveryUtils.getYear()+"年度物业费");
            propertyFee.setPayMoney(oldFee.getPayMoney());
            Calendar cal = Calendar.getInstance();
            cal.setTime(oldFee.getBeginDate());//设置起时间
            int year = cal.get(Calendar.YEAR);
            cal.set(Calendar.YEAR,year+1);
            propertyFee.setBeginDate(cal.getTime());

            cal.setTime(oldFee.getEndDate());//设置终时间
            year = cal.get(Calendar.YEAR);
            cal.set(Calendar.YEAR,year+1);
            propertyFee.setEndDate(cal.getTime());
            propertyFee.setPayState(StateProperty.PAY_NO);
            propertyFeeRepository.save(propertyFee);
            propertyFeeList.clear();
            propertyFeeList.add(propertyFee);
        }

        return propertyFeeList;
    }

    public boolean addFeeByRoom(PropertyFee  propertyFee){
        propertyFeeRepository.save(propertyFee);
        return true;
    }

    @Override
    public boolean addRoom(Room room) {
        try {
            roomRepository.save(room);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<Room> getRoomByUser(UserInfo userInfo) {
       List<Room> roomList = roomRepository.getRoomsByUserId(userInfo.getUserId());
        return roomList;
    }

}