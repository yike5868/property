package com.zl.property.repository;

import com.zl.property.model.hib.server.Photo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PhotoRepository extends CrudRepository<Photo, Long> {
}
