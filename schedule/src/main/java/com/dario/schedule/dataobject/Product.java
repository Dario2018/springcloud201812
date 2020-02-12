package com.dario.schedule.dataobject;

import com.dario.schedule.annotation.ExcelAnnotation;
import lombok.Data;

import java.util.Date;

/**
 * created by dario
 * 2018-12-29
 * */
@Data
public class Product {

    @ExcelAnnotation(id = 1,name={"产品名称","产品名称"},width = 5000)
    private String name;

    @ExcelAnnotation(id=2,name={"产品价格","产品价格"},width = 5000)
    private double price;

    @ExcelAnnotation(id=3,name={"产品价格","产品价格"},width = 5000)
    private Date date;
}
