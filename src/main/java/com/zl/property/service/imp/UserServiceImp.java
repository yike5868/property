package com.zl.property.service.imp;

import com.zl.property.model.hib.UserInfo;
import com.zl.property.repository.UserInfoRepository;
import com.zl.property.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    static Logger logger = LoggerFactory.getLogger(UserServiceImp.class);

    @Autowired
    UserInfoRepository userInfoRepository ;


    public UserInfo findUserInfoByUserName(UserInfo userInfo){

        UserInfo backUserInfo = userInfoRepository.findUserInfoByUserName(userInfo.getUserName());
        if(backUserInfo!=null && backUserInfo.getPassword().equals(userInfo.getPassword())){
            return backUserInfo;
        }
        return null;
    }

    @Override
    public UserInfo register(UserInfo userInfo) {
        UserInfo s = userInfoRepository.save(userInfo);
        return s;
    }

}
