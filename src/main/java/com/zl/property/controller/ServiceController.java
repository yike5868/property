package com.zl.property.controller;

import com.alibaba.fastjson.JSON;
import com.zl.property.model.dto.ResultDto;
import com.zl.property.model.hib.server.Repair;
import com.zl.property.model.hib.utils.Banner;
import com.zl.property.service.ServiceService;
import com.zl.property.service.imp.UserServiceImp;
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


}
