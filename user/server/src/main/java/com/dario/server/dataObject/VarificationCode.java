package com.dario.server.dataObject;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "varification_code")
public class VarificationCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String phone;

    private String code;

    private Boolean status;

    private Date createTime;

    private Date updateTime;
}
