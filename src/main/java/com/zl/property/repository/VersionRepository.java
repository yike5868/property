package com.zl.property.repository;

import com.zl.property.model.hib.property.Room;
import com.zl.property.model.hib.property.VersionDTO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VersionRepository extends CrudRepository<VersionDTO, Long> {

    VersionDTO getVersionDTOByType(String type);
}