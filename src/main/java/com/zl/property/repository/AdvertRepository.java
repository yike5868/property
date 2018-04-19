package com.zl.property.repository;

import com.zl.property.model.hib.utils.Advert;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AdvertRepository  extends CrudRepository<Advert, Long> {
    List<Advert> findAdvertByVerison (String version);
}
