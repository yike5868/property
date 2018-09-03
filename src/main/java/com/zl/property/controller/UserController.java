package com.zl.property.controller;

import com.alibaba.fastjson.JSON;
import com.zl.property.config.WebSecurityConfig;
import com.zl.property.model.dto.ResultDto;
import com.zl.property.model.dto.RoomItem;
import com.zl.property.model.hib.UserInfo;
import com.zl.property.model.hib.property.Room;
import com.zl.property.model.hib.property.VersionDTO;
import com.zl.property.model.hib.utils.Banner;
import com.zl.property.service.ServiceService;
import com.zl.property.service.UserService;
import com.zl.property.service.imp.UserServiceImp;
import com.zl.property.utils.JsonObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@RequestMapping(value = "/user",produces="text/plain;charset=UTF-8")
public class UserController {
    static Logger logger = LoggerFactory.getLogger(UserServiceImp.class);

    @Autowired
    UserService userService;
    @Autowired
    ServiceService serviceService;


    /**
     * 登录
     * @param session
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public  String login(@RequestBody UserInfo userInfo, HttpSession session) {
        logger.info("登录查询用户,UserName{},详细入参:{}",userInfo.getUserName(), JsonObjectUtils.objectToJson(userInfo));
        ResultDto resultDto = new ResultDto();
        UserInfo userInfoBack = userService.findUserInfoByUserName(userInfo);
        List<Room> roomList =serviceService.getRoomByUser(userInfoBack);
        if(userInfoBack == null){
            resultDto.setMessage("登录失败，请检查用户名或密码！");
            resultDto.setHasSuccess(true);
            resultDto.setSuccess(false);
        }else {
            logger.info("查询的房间数据Wie"+JsonObjectUtils.objectToJson(roomList));
            userInfoBack.setRoomList(roomList);
            resultDto.setData(userInfoBack);
            // 设置session
            session.setAttribute(WebSecurityConfig.SESSION_KEY, userInfoBack);
        }

        logger.info("登录查询用户返回,UserName{},返回:{}",userInfo.getUserName(), JsonObjectUtils.objectToJson(resultDto));
        return JSON.toJSONString(resultDto);
    }


    /**
     * 登录
     * @param session
     * @return
     */
    @PostMapping("/register")
    @ResponseBody
    public  String register(@RequestBody UserInfo userInfo, HttpSession session) {
        ResultDto resultDto = new ResultDto();
        UserInfo userInfo1 = userService.findUserInfoByUserName(userInfo);
        if(userInfo1!=null){
            resultDto.setMessage("用户名重复！");
            resultDto.setHasSuccess(true);
            resultDto.setSuccess(false);
            return JSON.toJSONString(resultDto);
        }
        UserInfo userInfoSend = userService.register(userInfo);

        if(userInfoSend == null){
            resultDto.setMessage("注册失败，请检查用户名或密码！");
            resultDto.setHasSuccess(true);
            resultDto.setSuccess(false);
        }else{
            resultDto.setData(userInfoSend);
        }

        return JSON.toJSONString(resultDto);
    }




    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 移除session
        session.removeAttribute(WebSecurityConfig.SESSION_KEY);
        return "redirect:/login";
    }

    /**
     * 获取公共单元
     * @return
     */
    @PostMapping("/findRoom")
    @ResponseBody
    public  String findRoom(@RequestBody RoomItem roomItem) {
        ResultDto resultDto = new ResultDto();
        List<RoomItem> roomItemList = null;
        if(roomItem == null||roomItem.getType() == null||"".equals(roomItem.getType())){
            roomItemList = userService.findAllMicrodistrict();
        }else if("microdistrict".equals(roomItem.getType())){
            roomItemList = userService.findBuildingByMicrodistrictId(roomItem.getId());
        }else if("building".equals(roomItem.getType())){
            roomItemList= userService.findUnitByBuildingId(roomItem.getId());
        }else if("unit".equals(roomItem.getType())){
            roomItemList = userService.findRoomByUnitId(roomItem.getId());
        }

        if(roomItemList == null||roomItemList.size()<1){
            resultDto.setMessage("获取失败！请联系小区负责人");
            resultDto.setHasSuccess(true);
            resultDto.setSuccess(false);
        }else{
            resultDto.setData(roomItemList);
        }
        return JSON.toJSONString(resultDto);
    }


    /**
     * 获取Android版本信息
     * @return
     */
    @PostMapping("/getAndroidVersion")
    @ResponseBody
    public  String getAndroidVersion() {
        VersionDTO versionDTO = userService.getAndroidVersion();
        ResultDto resultDto = new ResultDto();
        if(versionDTO == null){
            resultDto.setMessage("获取失败！请联系小区负责人");
            resultDto.setHasSuccess(true);
            resultDto.setSuccess(false);
        }else{
            resultDto.setData(versionDTO);
        }

        return JSON.toJSONString(resultDto);
    }


    /**
     * 增加房间
     */
    @PostMapping("/addRoomOnce")
    @ResponseBody
    public  String addRoomOnce() {
       boolean bbb =  userService.addRoomOnce();
        ResultDto resultDto = new ResultDto();
        resultDto.setSuccess(bbb);

        return JSON.toJSONString(resultDto);
    }


    /**
     * 增加房间
     */
    @PostMapping("/getBanner")
    @ResponseBody
    public  String getBanner() {
       List<Banner>  bannerList = userService.findListBanner();

        ResultDto resultDto = new ResultDto();
        if(bannerList == null){
            resultDto.setMessage("获取失败");
            resultDto.setHasSuccess(true);
            resultDto.setSuccess(false);
        }else{
            resultDto.setData(bannerList);
        }
        logger.info(JSON.toJSONString(resultDto));
        return JSON.toJSONString(resultDto);
    }
}
