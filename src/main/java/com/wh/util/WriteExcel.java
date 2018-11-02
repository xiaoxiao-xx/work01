package com.wh.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 往excel里写入数据
 * 写入前先删除sheet的每行数据（固定行行除外）
 * 准备文档输出流，写入才会生效
 * 每行每列循环为cell赋值
 * 准备文档输出流，写入
 * 最后刷新关闭输出流
 *
 * @author shawn
 */
public class WriteExcel<T> {

    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";

    public static void main(String[] args) {
        List<User> listU = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User u = new User("xiao", "12", "nan");
            listU.add(u);
        }
        new WriteExcel().writeExcel(listU, "E:/travel.xlsx");
    }

    public void writeExcel(List<T> list, String path) {
        OutputStream out = null;
        try {
            File file = new File(path);
            Workbook workbook = getWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);
            int rowNum = sheet.getLastRowNum();
            for (int i = 0; i < rowNum; i++) {
                Row row = sheet.getRow(i);
                sheet.removeRow(row);
            }
            out = new FileOutputStream(file);
            workbook.write(out);
            for (int i = 0; i < list.size(); i++) {
                Row row = sheet.createRow(i + 3);
                Field[] fields = list.get(i).getClass().getDeclaredFields();
                //在一行内循环写入数据
                for (int j = 0; j < fields.length; j++) {
                    int n = 0;
                    for (Field field : fields) {
                        field.setAccessible(true);
                        Cell cell = row.createCell(n++);
                        String val = String.valueOf(field.get(list.get(i)));
                        cell.setCellValue(val);
                    }
                }
            }
            out = new FileOutputStream(file);
            workbook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("导出成功");
    }

    /**
     * 获取excel的版本，返回合适的WorkBook对象
     *
     * @param file 文件对象
     * @return WorkBook
     * @throws IOException
     */
    private Workbook getWorkbook(File file) throws IOException {
        Workbook wb = null;
        FileInputStream in = new FileInputStream(file);
        if (file.getName().endsWith(EXCEL_XLS)) {
            wb = new HSSFWorkbook(in);
        } else if (file.getName().endsWith(EXCEL_XLSX)) {
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }
}
