package com.dario.goods.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table(name = "category")
public class GoodsCategory {
    //类目主键（递增）
    @Id
    @GeneratedValue
    private Integer categoryId;
    //类目名
    private String categoryName;
    //类目编号(与Goods类中字段)
    private Integer categoryType;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
}
