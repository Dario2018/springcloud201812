package com.dario.schedule.reportExcel;

import com.dario.schedule.excelUtil.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * 功能描述：导出报表
 * */
public class ReportExcel {

    /**
     * Excel导出的公共方法
     * 记录大于50000条时导入的格式为.xlsx ,小于50000条时导出的格式.xls
     * @param list 需要导出的列表数据
     * @param title 导出文件标题
     * @param className 导出的pojo对象类名
     * @param exportType 不同的导出模板选择
     * @param response
     * @param request
     * */
    public void excelExport(List list, String title, Class className, Integer exportType, HttpServletRequest request, HttpServletResponse response) throws IOException {

        OutputStream outputStream=response.getOutputStream();
        try {
            ExcelUtil excel=new ExcelUtil();
            if (list!=null&&list.size()>ExcelUtil.EXPORT_LEAST_SIZE)
                dealBigNumber(list,title,className,exportType,response,request,outputStream,excel);

        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println(e.getMessage());
        }finally {
            outputStream.close();
        }


    }


    private void dealBigNumber(List list,String title,Class className,Integer exportType,
    HttpServletResponse response,HttpServletRequest request,OutputStream outputStream,ExcelUtil excel) throws Exception{
        HSSFWorkbook hss=null;
        if (exportType==null)
            hss=excel.exportExcel(list,title,className,0);
        else
            hss=excel.exportExcel(list,title,className,exportType);

        String disposition="attachment;filename="+ URLEncoder.encode(title+".xls","UTF-8");
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Content-disposition",disposition);
        hss.write(outputStream);
    }

}
