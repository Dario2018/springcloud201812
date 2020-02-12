package com.dario.schedule.controller;

import com.dario.schedule.dataobject.Product;
import com.dario.schedule.reportExcel.ReportExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/exportExcel")
public class ExportExcelController {


    @RequestMapping("/1")
    public String print(HttpServletRequest request, HttpServletResponse response) throws Exception{
             List<Product> list =new ArrayList<>();
        for (int i=0;i<100000;i++){
            Product product=new Product();
            product.setName("陈德达"+i);
            product.setPrice(20000);
            product.setDate(new Date());
            list.add(product);
        }
        ReportExcel reportExcel=new ReportExcel();
        reportExcel.excelExport(list,"测试",Product.class,0,request,response);
        return "ok";
    }

}
