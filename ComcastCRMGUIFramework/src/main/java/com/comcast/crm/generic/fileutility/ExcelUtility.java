package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility 
{
	public String getDataFromExcel(String sheetName,int rowNum,int cellNum) throws Throwable, IOException
	{
		FileInputStream fis=new FileInputStream("./testData/selenium.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		String data=wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum).getStringCellValue();
		
		return data;
	}
	
	public int getRowCount(String sheetName) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis1=new FileInputStream("./testData/selenium.xlsx");
		Workbook wb1=WorkbookFactory.create(fis1);
		int rowCount=wb1.getSheet(sheetName).getLastRowNum();
		
		return rowCount;
	}
	
	public void setDataIntoExcel(String sheetName,int rowNum,int cellNum,String data) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis2=new FileInputStream("./testData/selenium.xlsx");
		Workbook wb2=WorkbookFactory.create(fis2);
		wb2.getSheet(sheetName).getRow(rowNum).createCell(cellNum);
		
		FileOutputStream fos=new FileOutputStream("./testData/selenium.xlsx");
		wb2.write(fos);
		wb2.close();
	
	}
	
}
