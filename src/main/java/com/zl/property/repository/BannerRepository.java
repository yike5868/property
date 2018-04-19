package com.zl.property.repository;

import com.zl.property.model.hib.utils.Banner;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BannerRepository  extends CrudRepository<Banner, Long> {
    List<Banner> findBannerByVersion (String version);

}
