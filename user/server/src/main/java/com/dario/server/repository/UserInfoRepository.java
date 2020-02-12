package com.dario.server.repository;

import com.dario.server.dataObject.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserInfoRepository extends JpaRepository<UserInfo,String> {

    UserInfo findUserInfoByOpenid(@Param("openid") String openid);

    UserInfo queryByUsername(@Param("username") String username);

    UserInfo queryByPhone(@Param("phone") String phone);
}
