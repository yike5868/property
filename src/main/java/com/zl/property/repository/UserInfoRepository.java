package com.zl.property.repository;

import com.zl.property.model.hib.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends CrudRepository<UserInfo, Long> {
        UserInfo findUserInfoByUserName(String userName);
}
