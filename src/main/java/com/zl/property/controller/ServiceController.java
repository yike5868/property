package com.zl.property.controller;

import com.alibaba.fastjson.JSON;
import com.zl.property.model.dto.ResultDto;
import com.zl.property.model.hib.UserInfo;
import com.zl.property.model.hib.server.Repair;
import com.zl.property.service.ServiceService;
import com.zl.property.service.imp.UserServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/service")
public class ServiceController {
    @Autowired
    ServiceService serviceService;
    static Logger logger = LoggerFactory.getLogger(UserServiceImp.class);

    /**
     * 根据用户id获取所有报修订单
     * @return
     */
    @PostMapping("/getAllRepairByUserId")
    @ResponseBody
    public  String getAllRepairByUserId(@RequestBody Repair repair) {
        ResultDto resultDto = new ResultDto();
        List<Repair> registerBack = serviceService.getRepairsByUserId(repair);

        if(repair == null){
            resultDto.setErrMessage("没有查到订单!");
            resultDto.setHasSuccess(false);
            resultDto.setSuccess(true);
        }else{
            resultDto.setData(registerBack);
        }

        return JSON.toJSONString(resultDto);
    }
    /**
     * 根据用户id获取所有报修订单
     * @return
     */
    @PostMapping("/getRepairsByUserIdAndState")
    @ResponseBody
    public  String getRepairsByUserIdAndState(@RequestBody Repair repair) {
        ResultDto resultDto = new ResultDto();
        List<Repair> registerBack = serviceService.getRepairsByUserIdAndState(repair);

        if(repair == null){
            resultDto.setErrMessage("没有查到订单!");
            resultDto.setHasSuccess(false);
            resultDto.setSuccess(true);
        }else{
            resultDto.setData(registerBack);
        }

        return JSON.toJSONString(resultDto);
    }
     /**
     * 根据用户id获取所有报修订单
     * @return
     */
    @PostMapping("/saveRepair")
    @ResponseBody
    public  String saveRepair(@RequestBody Repair repair) {
        ResultDto resultDto = new ResultDto();
        Repair repairBack = serviceService.saveRepair(repair);
        if(repairBack == null){
            resultDto.setErrMessage("保存失败!请重试！");
            resultDto.setHasSuccess(true);
            resultDto.setSuccess(false);
        }else{
            resultDto.setData(repairBack);
        }

        return JSON.toJSONString(resultDto);
    }




}
