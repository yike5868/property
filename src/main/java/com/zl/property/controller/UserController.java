package com.zl.property.controller;

import com.alibaba.fastjson.JSON;
import com.zl.property.config.WebSecurityConfig;
import com.zl.property.model.dto.ResultDto;
import com.zl.property.model.hib.UserInfo;
import com.zl.property.model.hib.property.Building;
import com.zl.property.model.hib.property.Microdistrict;
import com.zl.property.model.hib.property.Room;
import com.zl.property.model.hib.property.Unit;
import com.zl.property.service.UserService;
import com.zl.property.service.imp.UserServiceImp;
import com.zl.property.utils.JsonObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@RequestMapping(value = "/user",produces="text/plain;charset=UTF-8")
public class UserController {
    static Logger logger = LoggerFactory.getLogger(UserServiceImp.class);

    @Autowired
    UserService userService;



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
        if(userInfoBack == null){
            resultDto.setErrMessage("登录失败，请检查用户名或密码！");
            resultDto.setHasSuccess(true);
            resultDto.setSuccess(false);
        }else {
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
            resultDto.setErrMessage("用户名重复！");
            resultDto.setHasSuccess(true);
            resultDto.setSuccess(false);
            return JSON.toJSONString(resultDto);
        }
        UserInfo userInfoSend = userService.register(userInfo);

        if(userInfoSend == null){
            resultDto.setErrMessage("注册失败，请检查用户名或密码！");
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
     * 获取小区
     * @return
     */
    @PostMapping("/findAllMicrodistrict")
    @ResponseBody
    public  String findAllMicrodistrict() {
        ResultDto resultDto = new ResultDto();

        List<Microdistrict> microdistrictList = userService.findAllMicrodistrict();
        if(microdistrictList == null){
            resultDto.setErrMessage("获取小区失败！请联系小区负责人");
            resultDto.setHasSuccess(true);
            resultDto.setSuccess(false);
        }else{
            resultDto.setData(microdistrictList);
        }
        return JSON.toJSONString(resultDto);
    }
    /**
     * 获取楼栋
     * @return
     */
    @PostMapping("/findBuildingByMicrodistrictId")
    @ResponseBody
    public  String findBuildingByMicrodistrictId(Microdistrict microdistrict) {
        ResultDto resultDto = new ResultDto();

        List<Building> buildingList = userService.findBuildingByMicrodistrictId(microdistrict);
        if(buildingList == null){
            resultDto.setErrMessage("获取楼栋失败！请联系小区负责人");
            resultDto.setHasSuccess(true);
            resultDto.setSuccess(false);
        }else{
            resultDto.setData(buildingList);
        }
        return JSON.toJSONString(resultDto);
    }

    /**
     * 获取单元
     * @return
     */
    @PostMapping("/findUnitByBuildingId")
    @ResponseBody
    public  String findUnitByBuildingId(Building building) {
        ResultDto resultDto = new ResultDto();

        List<Unit> unitList = userService.findUnitByBuildingId(building);
        if(unitList == null){
            resultDto.setErrMessage("获取单元失败！请联系小区负责人");
            resultDto.setHasSuccess(true);
            resultDto.setSuccess(false);
        }else{
            resultDto.setData(unitList);
        }
        return JSON.toJSONString(resultDto);
    }
    /**
     * 获取单元
     * @return
     */
    @PostMapping("/findRoomByUnitId")
    @ResponseBody
    public  String findRoomByUnitId(Unit unit) {
        ResultDto resultDto = new ResultDto();

        List<Room> roomList = userService.findRoomByUnitId(unit);
        if(roomList == null){
            resultDto.setErrMessage("获取房间失败！请联系小区负责人");
            resultDto.setHasSuccess(true);
            resultDto.setSuccess(false);
        }else{
            resultDto.setData(roomList);
        }
        return JSON.toJSONString(resultDto);
    }

}
