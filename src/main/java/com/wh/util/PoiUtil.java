package com.wh.util;

import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wh.dao.TravelMapper;
import com.wh.pojo.Business;

@Service
public class PoiUtil {
	@Autowired
	private TravelMapper travelMapper;
	private Sheet sheet; // 表格类实例
	LinkedList[] result; // 保存每个单元格的数据 ，使用的是一种链表数组的结构
	static String[] list = new String[30];
	//static String fileName;

	public String getFileName(String filePath){
		File tempFile = new File(filePath.trim());
		String fileName = tempFile.getName();
		return fileName;
	}
	// 读取excel文件，创建表格实例
	public void loadExcel(String filePath) {
		//File tempFile = new File(filePath.trim());
		//fileName = tempFile.getName();
		//System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!"+fileName);
		System.out.println("$$$$$$$$$$$$$$$$$"+filePath);
		String fileName=getFileName(filePath);
		System.out.println("+_+_+_+_+_+_+_+_+_+"+fileName);
		FileInputStream inStream = null;
		try {
			inStream = new FileInputStream(new File(filePath));
			Workbook workBook = WorkbookFactory.create(inStream);

			sheet = workBook.getSheetAt(0);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (inStream != null) {
					inStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 获取单元格的值
	private String getCellValue(Cell cell) {
		String cellValue = "";
		DataFormatter formatter = new DataFormatter();
		if (cell != null) {
			// 判断单元格数据的类型，不同类型调用不同的方法
			switch (cell.getCellType()) {
			// 数值类型
			case Cell.CELL_TYPE_NUMERIC:
				// 进一步判断 ，单元格格式是日期格式
				if (DateUtil.isCellDateFormatted(cell)) {
					cellValue = formatter.formatCellValue(cell);
				} else {
					// 数值
					double value = cell.getNumericCellValue();
					int intValue = (int) value;
					cellValue = value - intValue == 0 ? String.valueOf(intValue) : String.valueOf(value);
				}
				break;
			case Cell.CELL_TYPE_STRING:
				cellValue = cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				cellValue = String.valueOf(cell.getBooleanCellValue());
				break;
			// 判断单元格是公式格式，需要做一种特殊处理来得到相应的值
			case Cell.CELL_TYPE_FORMULA: {
				try {
					cellValue = String.valueOf(cell.getNumericCellValue());
				} catch (IllegalStateException e) {
					cellValue = String.valueOf(cell.getRichStringCellValue());
				}

			}
				break;
			case Cell.CELL_TYPE_BLANK:
				cellValue = "";
				break;
			case Cell.CELL_TYPE_ERROR:
				cellValue = "";
				break;
			default:
				cellValue = cell.toString().trim();
				break;
			}
		}
		return cellValue.trim();
	}

	// 初始化表格中的每一行，并得到每一个单元格的值
	public void init() {
		int rowNum = sheet.getLastRowNum();
		result = new LinkedList[rowNum];
		for (int i = 0; i < rowNum; i++) {
			Row row = sheet.getRow(i);
			// 每有新的一行，创建一个新的LinkedList对象
			result[i] = new LinkedList();
			for (int j = 0; j < row.getLastCellNum(); j++) {
				Cell cell = row.getCell(j);
				// 获取单元格的值
				String str = getCellValue(cell);
				if ("".equals(str))
					str = null;
				// 将得到的值放入链表中
				result[i].add(str);
			}
		}
	}

	// HashMap<String,String> map = new HashMap<>();
	// Travel t=new Travel();
	// 控制台打印保存的表格数据
	public void show(String filePath) throws Exception {
		System.out.println("initintintitnitnitntitnitnti"+filePath);
		String fileName=getFileName(filePath);
		System.out.println(fileName);
		System.out.println("一共有" + (result.length - 3) + "条数据");
		for (int i = 3; i < result.length; i++) {
			for (int j = 0; j < result[i].size(); j++) {
				list[j] = (String) result[i].get(j);
			}
			Business business = new Business();
			business.setName(list[1]);
			business.setDepartment(list[2]);
			business.setDepartmentcost(list[3]);
			business.setStafflevel(list[4]);
			business.setPurpose(list[5]);
			business.setTrip(list[6]);
			business.setStartdate(list[7]);
			business.setGoff(list[8]);
			business.setEnddate(list[9]);
			business.setEndff(list[10]);
			business.setDays(list[11]);
			business.setCompanyorder(list[12]);
			business.setOtherorder(list[13]);
			business.setTravelstandard(list[14]);
			business.setLivestandard(list[15]);
			business.setCompanyset(list[16]);
			business.setPersonalset(list[17]);
			business.setTrafficperday(list[18]);
			business.setTrafficreality(list[19]);
			business.setTrafficreal(list[20]);
			business.setLiveperday(list[21]);
			business.setLivereality(list[22]);
			business.setLivereal(list[23]);
			business.setSubsidy(list[24]);
			business.setSubplan(list[25]);
			business.setRemarks(list[26]);
			business.setFilename(fileName);
			save(business);
		}

	}

	// 存储数据到mysql数据库
	public void save(Business business) throws Exception {
	
		travelMapper.insert(business);

	}

	// 先查询这个文件在数据库是否有数据
	public int isExistfileName(String filePath) throws Exception {
		// List<Business> a = travelMapper.select( business);
		String fileName=getFileName(filePath);
		System.out.println(filePath);
		System.out.println("????????????????"+fileName);
		int num = travelMapper.select(fileName);
		return num;
		
	}

	// 删除同名文件数据
	public void delFileName(String filePath) throws Exception {
		String fileName=getFileName(filePath);
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%"+filePath);
		System.out.println("///////"+fileName);
		 travelMapper.delete(fileName);

	}

}
