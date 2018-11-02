package com.wh.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.lang.reflect.Field;
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
    private OutputStream out = null;
    private Workbook workbook;
    private Sheet sheet;
    private File file;
    private String path;

    public WriteExcel(String path) throws IOException {
        this.path = path;
        this.file = new File(path);
        this.workbook = getWorkbook(file);
        this.sheet = workbook.getSheetAt(0);
    }

    /**
     * @param list  与excel字段对应对象集合
     * @param space 保留前space行
     */
    public void writeExcel(List<T> list, int space) {
        try {
            int rowNum = sheet.getLastRowNum();

            CellStyle lastRowStyle = getLastRowStyle(rowNum);
            CellStyle style = getComtextStyle(space);
            deleteNotInSpace(space, rowNum);

            Field[] fields = null;
            for (int i = 0; i < list.size(); i++) {
                Row row = sheet.createRow(i + space);
                fields = list.get(i).getClass().getDeclaredFields();
                //在一行内循环写入数据
                for (int j = 0; j < fields.length; j++) {
                    int n = 0;
                    for (Field field : fields) {
                        field.setAccessible(true);
                        Cell cell = row.createCell(n++);
                        String val = String.valueOf(field.get(list.get(i)));
                        cell.setCellValue(val);
                        cell.setCellStyle(style);
                    }
                }
            }
            setlastRow(list.size(),fields.length, space, lastRowStyle);
            flushWorkBook();
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
    }

    private CellStyle getLastRowStyle(int rowNum) {
        Row row = sheet.getRow(rowNum);
        return row.getCell(0).getCellStyle();
    }

    private CellStyle getComtextStyle(int space) {
        Row row = sheet.getRow(space);
        return row.getCell(0).getCellStyle();
    }

    private void deleteNotInSpace(int space, int rowNum) throws IOException {
        for (int i = space; i <= rowNum; i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                sheet.removeRow(row);
            }
        }
        flushWorkBook();
    }

    private void flushWorkBook() throws IOException {
        out = new FileOutputStream(file);
        workbook.write(out);
    }

    public void setFirtRow(String value) throws IOException {
        Row row = sheet.getRow(0);
        Cell cell = row.getCell(0);
        CellStyle cellStyle = cell.getCellStyle();
        cell.setCellValue(value);
        cell.setCellStyle(cellStyle);
        flushWorkBook();
    }

    private void setlastRow(int size, int len, int space, CellStyle lastRowStyle) {
        Row row = sheet.createRow(size + space);
        for (int i = 0; i < len; i++) {
            Cell cell = row.createCell(i);
            cell.setCellStyle(lastRowStyle);
            if (i == 0) {
                cell.setCellValue("合计");
            }else {
                cell.setCellValue(" ");
            }
        }
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
