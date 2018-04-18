package com.zl.property.service;

import com.zl.property.model.hib.UserInfo;

public interface UserService {
    UserInfo findUserInfoByUserName(UserInfo userInfo);
    UserInfo register(UserInfo userInfo);
}
