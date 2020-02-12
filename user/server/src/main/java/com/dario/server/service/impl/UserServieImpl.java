package com.dario.server.service.impl;

import com.dario.server.dataObject.UserInfo;
import com.dario.server.repository.UserInfoRepository;
import com.dario.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServieImpl implements UserService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserInfo findByOpenId(String openid) throws Exception {
        return userInfoRepository.findUserInfoByOpenid(openid);
    }

    /*用户名校验用户的合法性*/
    @Override
    public UserInfo queryByUsername(String username) throws Exception {
        return userInfoRepository.queryByUsername(username);
    }

    /*手机号校验用户名的合法性*/
    @Override
    public UserInfo queryByPhone(String phone) throws Exception {
        return userInfoRepository.queryByPhone(phone);
    }

}
