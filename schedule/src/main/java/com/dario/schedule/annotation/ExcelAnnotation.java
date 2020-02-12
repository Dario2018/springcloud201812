package com.dario.schedule.annotation;

import java.lang.annotation.*;

/**
 * excel模板设置
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExcelAnnotation {
    //    Excel列ID（Excel排序序号）
    int id();

    //Excel 列名
    String[] name();

    //Excel 列宽
    int width() default 50000;
}
