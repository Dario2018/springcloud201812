package com.dario.server.controller;

import com.dario.server.dataObject.UserInfo;
import com.dario.server.dataObject.VarificationCode;
import com.dario.server.exception.LoginException;
import com.dario.server.service.UserService;
import com.dario.server.service.VarificationCodeService;
import com.dario.server.unums.LoginStatusEnum;
import com.dario.server.utils.KeyUtil;
import com.dario.server.utils.RandomNumber;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/app")
@Slf4j
public class AppLoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private VarificationCodeService varificationCodeService;

    @PostMapping(value = "/login")
    @CrossOrigin
    @ResponseBody
    public String loginByPwd(@RequestParam Map<String, String> map) {
        JsonObject jsonObject = new JsonObject();
        String username = map.get("username");
        String password = map.get("password");

        System.out.println(username);
        System.out.println(password);

        //二次校验是否为空 code:-1
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            jsonObject.addProperty("code", LoginStatusEnum.LOGIN_USERORPASSWORD_NULL.getCode());
            jsonObject.addProperty("messages", LoginStatusEnum.LOGIN_USERORPASSWORD_NULL.getMessages());
            return jsonObject.toString();
        }

        //密码BCrypt算法加密得到密码盐值-(123456)
//        String hashpw = BCrypt.hashpw(password, BCrypt.gensalt(10));
//        System.out.println(hashpw);
        //查询数据库账户和密码是否一致
//        String bcpwd="$2a$10$vyAy/.E4.TYtngqgrJrmw.9CyVvkdWMJKcoyOaqyiHDSiUmjqFE6G";

        try {
            UserInfo userInfo = userService.queryByUsername(username);
            if (null == userInfo) {
                jsonObject.addProperty("code", LoginStatusEnum.LOGIN_NOT_REGISTRY.getCode());
                jsonObject.addProperty("messages", LoginStatusEnum.LOGIN_NOT_REGISTRY.getMessages());
            }
            Optional.ofNullable(userInfo).ifPresent(userInfo1 -> {
                String bcpwd = userInfo.getPassword();
                boolean flag = BCrypt.checkpw(password, bcpwd);
                System.out.println(flag);
                if (flag) {
                    if (1 == userInfo1.getEnabled()) {
                        //判断用户是否被标记为锁定用户
                        jsonObject.addProperty("code", LoginStatusEnum.LOGIN_USER_BLOCKED.getCode());
                        jsonObject.addProperty("messages", LoginStatusEnum.LOGIN_SUCCESS.getMessages());
                    } else {
                        System.out.println("成功登录....");
                        //登录成功 保存登录状态到redis中，设置有效期为3个小时 code:0
                        jsonObject.addProperty("code", LoginStatusEnum.LOGIN_SUCCESS.getCode());
                        jsonObject.addProperty("messages", LoginStatusEnum.LOGIN_SUCCESS.getMessages());
                    }
                } else {
                    log.info("密码错误");
                    jsonObject.addProperty("code", LoginStatusEnum.LOGIN_USER_ERROR.getCode());
                    jsonObject.addProperty("messages", LoginStatusEnum.LOGIN_USER_ERROR.getMessages());
                }
            });
            jsonObject.addProperty("status", 200);
            return jsonObject.toString();
        } catch (Exception e) {
            log.error("获取用户失败，原因：【{}】", e);
            e.printStackTrace();
            jsonObject.addProperty("code", 100);
            throw new LoginException(LoginStatusEnum.LOGIN_EXCEPTION);
        }
    }

    /**
     * 获取手机验证码-6位,
     */
    @GetMapping(value = "/getVarifyCode", produces = "application/json;charset=utf-8")
    @CrossOrigin
    @ResponseBody
    public String getVarifyCode(@RequestParam(value = "phone", required = true) String phone) {
        JsonObject jsonObject = new JsonObject();
        if (StringUtils.isEmpty(phone)) {
            jsonObject.addProperty("status", -1);
            jsonObject.addProperty("message", "手机号不能为空！");
            return jsonObject.toString();
        }
        //先查询电话的有效性

        UserInfo userInfo = null;
        try {
            userInfo = userService.queryByPhone(phone);
        } catch (Exception e) {
            log.error("获取手机验证码发生异常：{}", e);
            throw new LoginException(LoginStatusEnum.GET_VARIFY_CODE);
        }
        if (userInfo == null) {
            jsonObject.addProperty("status", 0);
            jsonObject.addProperty("message", "该手机号还未注册，请先行注册。");
            return jsonObject.toString();
        }
        //生成一条6位随机数
        String code = RandomNumber.getRandomNumber(6);
        //todo 送到用户手机上

        //保存到数据库中
        VarificationCode varificationCode = new VarificationCode();
        varificationCode.setPhone(userInfo.getPhone());
        varificationCode.setUsername(userInfo.getUsername());
        varificationCode.setStatus(false);
        varificationCode.setCode(code);
        varificationCode.setCreateTime(new Date());
        try {
            varificationCodeService.insertVarification(varificationCode);
        } catch (Exception e) {
            log.error("手机验证码保存到数据库异常：{}", e);
            throw new LoginException(LoginStatusEnum.GET_VARIFY_CODE);
        }
        jsonObject.addProperty("status", 1);
        jsonObject.addProperty("message", code);
        return jsonObject.toString();
    }

    /**
     * 激活手机验证码
     */
    @PostMapping(value = "/activateVarifyCode", produces = "application/json;charset=utf-8")
    @CrossOrigin
    @ResponseBody
    public String activateVarifyCode(@RequestParam Map<String, String> map) {
        JsonObject jsonObject = new JsonObject();
        String phone = map.get("phone");
        String code = map.get("verification");
        if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(code)) {
            jsonObject.addProperty("status", -1);
            jsonObject.addProperty("message", "手机号或者验证码不能为空");
            return jsonObject.toString();
        }
        try {
            List<VarificationCode> varificationCodeList = varificationCodeService.findAllByPhone(phone);
            if (varificationCodeList == null || varificationCodeList.size() == 0) {
                jsonObject.addProperty("status", 1);
                jsonObject.addProperty("message", "手机号或验证码不正确");
            } else if (!code.equals(varificationCodeList.get(varificationCodeList.size() - 1).getCode())) {
                jsonObject.addProperty("status", 2);
                jsonObject.addProperty("message", "认证码已经失效");
            } else {
                VarificationCode varificationCode = varificationCodeList.get(varificationCodeList.size() - 1);
                varificationCode.setStatus(true);
                varificationCode.setUpdateTime(new Date());
                varificationCodeService.insertVarification(varificationCode);
                jsonObject.addProperty("status", 0);
                jsonObject.addProperty("message", "激活成功");
            }
            return jsonObject.toString();

        } catch (Exception e) {
            log.error("手机号码和验证码激活失败,原因：{}", e);
            e.printStackTrace();
            throw new LoginException(LoginStatusEnum.ACTIVATE_VARIFY_CODE);

        }

    }
}
