package com.wordpress.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileReader {

	XSSFWorkbook wb;
	XSSFSheet sheet;
	ConfigFileReader configReader;

	public ExcelFileReader() {
		try {
			configReader = new ConfigFileReader();
			File excelFile = new File(configReader.getExcelPath());
			FileInputStream excelfis = new FileInputStream(excelFile);
			wb = new XSSFWorkbook(excelfis);
			sheet = wb.getSheet(configReader.getExcelSheetName());
		} catch (Exception e) {
			System.out.println("Error in reading Excel File: " + e.getMessage());
		}
	}

	public String getUserName(String userType) {
		String uName = null;
		int rowNum = sheet.getLastRowNum() + 1;
		for (int i = 1; i < rowNum; i++) {
			if (sheet.getRow(i).getCell(0).getStringCellValue().equalsIgnoreCase(userType)) {
				Cell cell = sheet.getRow(i).getCell(1);
				if (cell.getCellType() == CellType.NUMERIC) {
					uName = NumberToTextConverter.toText(sheet.getRow(i).getCell(1).getNumericCellValue());
				} else {
					uName = sheet.getRow(i).getCell(1).getStringCellValue();
				}
			}
		}

		return uName;
	}

	public String getPassword(String userType) {
		String pwd = null;
		int rowNum = sheet.getLastRowNum() + 1;
		for (int i = 1; i < rowNum; i++) {
			if (sheet.getRow(i).getCell(0).getStringCellValue().equalsIgnoreCase(userType)) {
				Cell cell = sheet.getRow(i).getCell(2);
				if (cell.getCellType() == CellType.NUMERIC) {
					pwd = NumberToTextConverter.toText(sheet.getRow(i).getCell(2).getNumericCellValue());
				} else {
					pwd = sheet.getRow(i).getCell(2).getStringCellValue();
				}
			}
		}

		return pwd;
	}

	public void closeExcelWB() {
		try {
			wb.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
