package com.dario.server.controller;

import com.dario.server.VO.ResultVO;
import com.dario.server.constanst.ConstantUtil;
import com.dario.server.dataObject.UserInfo;
import com.dario.server.service.UserService;
import com.dario.server.unums.ResultEnum;
import com.dario.server.unums.RoleEnum;
import com.dario.server.utils.CookieUtil;
import com.dario.server.utils.ResultVOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 1=买家登陆
     * openid   用户微信登录的id
     * 设计思想：用户登录微信进入点餐公众号
     * ，获取openid,把其保存都cookie中或者GET传参，跳槽转发
     * 页面，通过html5 sessionStorage
     * 保存openid，
     */
    @RequestMapping("/buyer")
    public ResultVO buyer(@RequestParam("openid") String openid, HttpServletResponse response) {
        try {
            //用户是否存在
            UserInfo userInfo = userService.findByOpenId(openid);
            if (null == userInfo) {
                return ResultVOUtils.error(ResultEnum.LOGIN_FAIL);
            }
            //角色权限
            if (RoleEnum.BUYER.getCode() != userInfo.getRole()) {
                return ResultVOUtils.error(ResultEnum.ROLE_ERROR);
            }
            //登陆成功保存在cookie中
            CookieUtil.set(response, ConstantUtil.OPENID, openid, ConstantUtil.EXPIRE);
            return ResultVOUtils.success(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVOUtils.error(ResultEnum.LOGIN_FAIL);
        }
    }

    /**
     * 2=卖家登陆
     */
    @RequestMapping("/seller")
    public ResultVO seller(@RequestParam("openid") String openid, HttpServletRequest request, HttpServletResponse response) {

        try {
            //用户是否存在
            UserInfo userInfo = userService.findByOpenId(openid);
            if (null == userInfo) {
                return ResultVOUtils.error(ResultEnum.LOGIN_FAIL);
            }
            //角色权限
            if (RoleEnum.SELLER.getCode() != userInfo.getRole()) {
                return ResultVOUtils.error(ResultEnum.ROLE_ERROR);
            }
            //判断是否已经登陆
            Cookie cookie = CookieUtil.get(request, ConstantUtil.TOKEN);
            if (null != cookie &&
                    !StringUtils.isEmpty(stringRedisTemplate.opsForValue().get(String.format(ConstantUtil.TOKEN_TEMPLATE, cookie.getValue())))) {
                return ResultVOUtils.success(null);
            }

            //保存到redis中
            String token = UUID.randomUUID().toString();
            stringRedisTemplate.opsForValue().set(String.format(ConstantUtil.TOKEN_TEMPLATE, token), openid, ConstantUtil.EXPIRE, TimeUnit.SECONDS);
            //登陆成功保存在cookie中
            CookieUtil.set(response, ConstantUtil.TOKEN, token, ConstantUtil.EXPIRE);
            return ResultVOUtils.success(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVOUtils.error(ResultEnum.LOGIN_FAIL);
        }
    }

}
