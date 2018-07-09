package com.zl.property.service.imp;

import com.zl.property.model.hib.UserInfo;
import com.zl.property.model.hib.server.Photo;
import com.zl.property.model.hib.server.Repair;
import com.zl.property.model.hib.utils.Banner;
import com.zl.property.repository.BannerRepository;
import com.zl.property.repository.PhotoRepository;
import com.zl.property.repository.ServiceRepository;
import com.zl.property.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceServiceImp implements ServiceService {

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    PhotoRepository photoRepository;


    @Autowired
    BannerRepository bannerRepository;



    /**
     * 根据id获取服务订单
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
     * @param repair
     * @return
     */
    @Override
    public List<Repair> getRepairsByUserIdAndState(Repair repair) {
        List<Repair> repairs;
        if (repair.getState() == null)
            repairs = serviceRepository.findRepairsByUserIdAndPageIndexAndPageSize(repair.getUserId(),repair.getPageIndex(),repair.getPageSize());
        else
            repairs = serviceRepository.findRepairsByUserIdAndState(repair.getRoomId(), repair.getState());
        return repairs;
    }

    /**
     * 保存订单
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


}
