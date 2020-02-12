package com.dario.schedule.excelUtil;

import com.dario.schedule.annotation.ExcelAnnotation;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * excel工具类
 * */
public class ExcelUtil<T> {
    public static final int EXPORT_LEAST_SIZE=50000;

    /**
     * 1.获取表单导出数据
     * */
    public  HSSFWorkbook exportExcel(List<T> list,String title,Class className,Integer exportType) throws Exception{

        //获取属性
        Field[] fields=className.getDeclaredFields();
        List<Field> fieldList=new ArrayList<>();

        Arrays.stream(fields).forEach(field -> {
            if (field.isAnnotationPresent(ExcelAnnotation.class))
                fieldList.add(field);
        });
        //按照id进行排序
        Collections.sort(fieldList, new Comparator<Field>() {
            @Override
            public int compare(Field o1, Field o2) {
                return o1.getAnnotation(ExcelAnnotation.class).id()-o2.getAnnotation(ExcelAnnotation.class).id();
            }
        });

        int columsize=fieldList.size();
        int rowindex=0;

        //创建一个HSSFWorkbook对象（excel的文档对象）
        HSSFWorkbook hssfWorkbook=new HSSFWorkbook();
        //创建一个HSSFSheet对象（excel的表单）
        HSSFSheet hssfSheet=hssfWorkbook.createSheet();
        //创建行（EXCEL行）
        HSSFRow hssfSheetRow = hssfSheet.createRow(rowindex++);
        //设置行的高度
        hssfSheetRow.setHeight(Short.valueOf("380"));
        //创建单元格（从0开始）
        HSSFCell hssfCell=hssfSheetRow.createCell(Short.valueOf("0"));
        //样式对象
        HSSFCellStyle cellStyle=getCellStyle(hssfWorkbook,Short.valueOf("300"),Short.valueOf("500"));

        //给单元设置样式
        hssfCell.setCellStyle(cellStyle);

        hssfCell.setCellValue(title);
        if (getHuoResult(fieldList.isEmpty(),null==list,list.isEmpty()))
            return hssfWorkbook;

        //创建第二行，代表列名
        hssfSheetRow=hssfSheet.createRow(rowindex++);
        cellStyle=getCellStyle(hssfWorkbook,Short.valueOf("270"),Short.valueOf("500"));
        generateTitle(exportType,fieldList,columsize,hssfSheet,hssfSheetRow,cellStyle,hssfCell);

        //组装excel 的数据
        cellStyle=getCellStyle(hssfWorkbook,Short.valueOf("270"),Short.valueOf("500"));
        generateTitle(exportType,fieldList,columsize,hssfSheet,hssfSheetRow,cellStyle,hssfCell);

        //组装excel的数据
        cellStyle=getCellStyle(hssfWorkbook,Short.valueOf("220"),Short.valueOf("500"));//设置单元格式
        generateData(list,fieldList,columsize,rowindex,hssfSheet,cellStyle);

        /**
         * 第一个参数：从哪一行开始
         * 第二参数：到哪一行结束
         * 第三个参数：从那一列开始
         * 第四参数：到那一列结束
         * */
        hssfSheet.addMergedRegion(new CellRangeAddress(0,0,0,columsize-1));

        //固定表头（前一个参数代表，后一个参数单表行）
        hssfSheet.createFreezePane(0,1);

        return hssfWorkbook;

    }

    /**
     * 功能：组装列明
     * @param exportType 模板编号
     * @param fieldList 列名
     * @param columnsize 列数
     * @param hSheet sheet页
     * @param hRow 行
     * @param cellStyle 样式
     * */
    private void generateTitle(Integer exportType,List<Field> fieldList,int columnsize,HSSFSheet hssfSheet,HSSFRow hssfRow,HSSFCellStyle cellStyle,HSSFCell hssfCell){
        HSSFCell hssfCell1=null;
        for (int i=0;i<columnsize;i++){
            Field field=fieldList.get(i);
            if(field.isAnnotationPresent(ExcelAnnotation.class)){
                //获取该字段的注解对象
                ExcelAnnotation annotation=field.getAnnotation(ExcelAnnotation.class);
                hssfCell1=hssfRow.createCell((short)i);
                String colName=field.getAnnotation(ExcelAnnotation.class).name().length>exportType
                        ?field.getAnnotation(ExcelAnnotation.class).name()[exportType]
                        :field.getAnnotation(ExcelAnnotation.class).name()[0];
                hssfCell.setCellValue(colName);
                hssfCell.setCellStyle(cellStyle);
                hssfSheet.setColumnWidth(i,annotation.width());
            }
        }
    }

    /**
     * 组装excel的数据
     * @param list具体数据
     * @param fieldList 列名
     * @param columnsize 列数
     * @param rowindex 行数计数
     * @param hssfSheet sheet页
     * @param cellStyle 样式
     * */
    private int generateData(List<T> list,List<Field> fieldList,int columnsize,int rowindex,HSSFSheet hssfSheet,HSSFCellStyle cellStyle) throws NoSuchMethodException
    ,IllegalAccessException, InvocationTargetException {
        HSSFRow hssfRow=null;
        HSSFCell hssfCell=null;
        for (Object model:list){
            hssfRow=hssfSheet.createRow(rowindex++);
            //获取该类
            Class clazz=model.getClass();
            for (int i=0;i<columnsize;i++){
                Field field=fieldList.get(i);
                //获取方法名
                String methodName="get"+field.getName().substring(0,1).toUpperCase()+field.getName().substring(1);
                Method method=clazz.getMethod(methodName);
                try{
                    //获取该字段的注解对象
                    Object result=method.invoke(model);
                    hssfCell=hssfRow.createCell((short) i);
                    if (result!=null){
                        if (result.getClass().isAssignableFrom(Date.class)){
                            SimpleDateFormat format=new SimpleDateFormat("yyyy年MM月dd日");
                            result=format.format(result);
                        }
                        hssfCell.setCellValue(new HSSFRichTextString(result.toString()));
                    }else{
                        hssfCell.setCellValue(new HSSFRichTextString("-"));
                    }
                    hssfCell.setCellStyle(cellStyle);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
        }
        return rowindex;
    }

    /**
     * 获取或运算结果
     * */
    private static boolean getHuoResult(Boolean... bs){
        for (boolean b:bs) {
            if (b) return b;
        }
        return false;
    }


    /**
     *
     * 功能：设置excel表格默认样式
     * @param hssfWorkbook 需要导出Excel数据
     * @param fontHeight 字体粗度
     * @param boldWeight 表格线的粗度
     * */
    public HSSFCellStyle getCellStyle(HSSFWorkbook hssfWorkbook,short fontHeight,short boldWeight){
        HSSFCellStyle cellStyle;
        HSSFFont font;
        cellStyle=hssfWorkbook.createCellStyle();
//        cellStyle.setBorderBottom(new HSSFCellStyle().getBorderBottom());
        font=hssfWorkbook.createFont();
        font.setFontHeight(fontHeight);
        font.setFontName("宋体");
        cellStyle.setFont(font);
        cellStyle.setWrapText(true);
//        cellStyle.setAlignment(HSSFCellStyle.a);
        return cellStyle;
    }



}
