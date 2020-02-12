package com.dario.server.dataObject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author dario
 * @date 2019-01-21
 * @decription 用户信息表
 * */
@Data
@Entity
@Table(name = "user_info")
public class UserInfo {

    @Id
    private String id;//主键

    /*用户名*/
    private String username;

    /*密码*/
    private String password;

   /* 微信id*/
    private String openid;

   /* 角色（1买家，2-卖家）*/
    private Integer role;

    /*0-正常使用，1-锁定*/
    private Integer enabled;

    /*联系电话*/
    private String phone;

    /*地址*/
    private String address;

    /*年龄*/
    private Integer age;

   /* 性别：0-女，1-男*/
    private Integer sex;

    /*身份证号*/
    private String identificationCard;

    /*职称*/
    private String professional;

    private Date createTime;

    private Date updateTime;

}
