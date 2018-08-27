package com.zl.property.controller;

import com.alibaba.fastjson.JSON;
import com.zl.property.config.StateProperty;
import com.zl.property.model.dto.FeeUser;
import com.zl.property.model.dto.ResultDto;
import com.zl.property.model.hib.UserInfo;
import com.zl.property.model.hib.property.Room;
import com.zl.property.model.hib.server.PropertyFee;
import com.zl.property.model.hib.server.Repair;
import com.zl.property.model.hib.utils.Banner;
import com.zl.property.service.ServiceService;
import com.zl.property.service.imp.UserServiceImp;
import com.zl.property.utils.EveryUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/service",produces="text/plain;charset=UTF-8")
public class ServiceController {
      @Autowired
    ServiceService serviceService;

    static Logger logger = LoggerFactory.getLogger(UserServiceImp.class);

    /**
     * 根据用户id获取所有报修订单
     * @return
     */
    @PostMapping("/getRepairs")
    @ResponseBody
    public  String getRepairsByUserIdAndState(@RequestBody Repair repair) {
        ResultDto resultDto = new ResultDto();
        List<Repair> registerBack = serviceService.getRepairsByUserIdAndState(repair);

        if(repair == null){
            resultDto.setMessage("没有查到订单!");
            resultDto.setHasSuccess(false);
            resultDto.setSuccess(true);
        }else{
            resultDto.setData(registerBack);
        }

        return JSON.toJSONString(resultDto);
    }
     /**
     * 保存订单
     * @return
     */
    @PostMapping("/saveRepair")
    @ResponseBody
    public  String saveRepair(@RequestBody Repair repair) {
        ResultDto resultDto = new ResultDto();

        if(repair.getState() == null){
            repair.setState(StateProperty.REPAIR_CREATE);
        }

        Repair repairBack = serviceService.saveRepair(repair);
        if(repairBack == null){
            resultDto.setMessage("保存失败!请重试！");
            resultDto.setHasSuccess(true);
            resultDto.setSuccess(false);
        }else{
            resultDto.setMessage("提交成功！请等待");
            resultDto.setData(repairBack);
        }

        return JSON.toJSONString(resultDto);
    }


    /**
     * 根据用户版本获取banner
     */

    @PostMapping("/findBannerByVersion")
    @ResponseBody
    public  String findBannerByVersion(@RequestBody Banner banner) {
        ResultDto resultDto = new ResultDto();
        List<Banner> bannerList = serviceService.findBannerByVersion(banner);
        if(bannerList == null){
            //获取banner失败
            resultDto.setMessage("获取banner失败");
            resultDto.setHasSuccess(true);
            resultDto.setSuccess(false);
        }else{
            resultDto.setData(bannerList);
        }

        return JSON.toJSONString(resultDto);
    }
    /**
     * 保存banner
     */

    @PostMapping("/saveBanner")
    @ResponseBody
    public  String saveBanner(@RequestBody Banner banner) {
        ResultDto resultDto = new ResultDto();
        Banner backBanner = serviceService.saveBanner(banner);
        if(backBanner == null){
            //获取banner失败
            resultDto.setMessage("保存banner失败！");
            resultDto.setHasSuccess(true);
            resultDto.setSuccess(false);
        }else{
            resultDto.setData(backBanner);
        }
        return JSON.toJSONString(resultDto);
    }

    /**
     * 根据房间获取付款
     */

    @PostMapping("/getFee")
    @ResponseBody
    public  String getFee(@RequestBody FeeUser feeUser) {
        ResultDto resultDto = new ResultDto();
        List<PropertyFee> propertyFees= serviceService.getFeeByRoom(feeUser);
        if(propertyFees == null){
            if(feeUser.getPageIndex()>1){
                resultDto.setMessage("没有更多订单了！");
            }else
            resultDto.setMessage("请联系物业确认并生成订单！");
            resultDto.setHasSuccess(true);
            resultDto.setSuccess(false);
        }else{
            resultDto.setData(propertyFees);
        }
        return JSON.toJSONString(resultDto);
    }

    /**
     * 根据房间获取付款
     */

    @PostMapping("/addFeeByRoom")
    @ResponseBody
    public  String addFeeByRoom(@RequestBody  PropertyFee propertyFee) {
        ResultDto resultDto = new ResultDto();
        boolean b = serviceService.addFeeByRoom(propertyFee);
        if(!b){
            //获取banner失败
            resultDto.setMessage("添加失败！");
            resultDto.setHasSuccess(true);
            resultDto.setSuccess(false);
        }else{
            resultDto.setSuccess(true);
        }
        return JSON.toJSONString(resultDto);
    }

    /**
     * 添加房间
     */

    @PostMapping("/addRoom")
    @ResponseBody
    public  String addRoom(@RequestBody Room room) {
        ResultDto resultDto = new ResultDto();
        boolean b = serviceService.addRoom(room);
        if(!b){
            resultDto.setMessage("添加失败！");
            resultDto.setHasSuccess(true);
            resultDto.setSuccess(false);
        }else{
            resultDto.setSuccess(true);
        }
        return JSON.toJSONString(resultDto);
    }


    /**
     * 添加房间
     */

    @PostMapping("/addRoomUser")
    @ResponseBody
    public  String addRoomUser(@RequestBody UserInfo userInfo) {
        ResultDto resultDto = new ResultDto();
        boolean b = serviceService.addRoomByUser(userInfo);
        if(!b){
            resultDto.setMessage("添加失败！");
            resultDto.setHasSuccess(true);
            resultDto.setSuccess(false);
        }else{
            resultDto.setSuccess(true);
        }
        return JSON.toJSONString(resultDto);
    }

    /**
     * 添加房间
     */

    @PostMapping("/getRoomByUser")
    @ResponseBody
    public  String getRoomByUser(@RequestBody UserInfo userInfo) {
        ResultDto resultDto = new ResultDto();
        List<Room> roomList = serviceService.getRoomByUser(userInfo);
        if(EveryUtils.isEmpty(roomList)){
            resultDto.setMessage("请添加房屋！");
            resultDto.setHasSuccess(true);
            resultDto.setSuccess(false);
        }else{
            resultDto.setData(roomList);
        }
        return JSON.toJSONString(resultDto);
    }


    /**
     * 获取根据useid roomid 获取用户支付宝订单信息
     */
    @PostMapping("/getOrderInfo")
    @ResponseBody
    public  String getOrderInfo(@RequestBody List<PropertyFee> propertyFeeList) {
        PropertyFee propertyFee = propertyFeeList.get(0);
        String orderInfo = serviceService.getOrderInfo(propertyFee);
        ResultDto resultDto = new ResultDto();
        if(EveryUtils.isEmpty(orderInfo)){
            resultDto.setMessage("生成订单失败！");
            resultDto.setHasSuccess(true);
            resultDto.setSuccess(false);
        }else{
            resultDto.setData(orderInfo);
        }
        return JSON.toJSONString(resultDto);
    }

}
