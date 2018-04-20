package com.zl.property.controller;

import com.alibaba.fastjson.JSON;
import com.zl.property.config.WebSecurityConfig;
import com.zl.property.model.dto.ResultDto;
import com.zl.property.model.hib.UserInfo;
import com.zl.property.service.UserService;
import com.zl.property.service.imp.UserServiceImp;
import com.zl.property.utils.JsonObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


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

}
