package com.dario.server.service;

import com.dario.server.dataObject.UserInfo;

public interface UserService {

    UserInfo findByOpenId(String openid) throws Exception;

    UserInfo queryByUsername(String username) throws Exception;

    UserInfo queryByPhone(String phone) throws Exception;
}
