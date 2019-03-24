package commonFunctions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ExcelConnector {

	public static Object[][] ExcelRead(String excelName,String sheetName) throws IOException{
		Hashtable<String, String> hashdata1 = new Hashtable<String, String>();
	
		Object[][] Obj = new Object[1][1];

		String filePath = excelName;
		File file =    new File(filePath);

		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook1 = new XSSFWorkbook(inputStream);
		//Read sheet inside the workbook by its name

		Sheet sheet1=workbook1.getSheet(sheetName);

		//Find number of rows in excel file
		int rowCount =sheet1.getLastRowNum();
		System.out.println(rowCount);
		Row row = sheet1.getRow(0);
		 
		//Create a loop to print cell values in a row

		for (int j = 0; j < row.getLastCellNum(); j++) {
			//System.out.println(sheet1.getRow(0).getCell(j).getStringCellValue());
			//System.out.println(sheet1.getRow(1).getCell(j).getStringCellValue());
			//Obj[0][j]=sheet1.getRow(0).getCell(j).getStringCellValue();
			//Obj[1][j]=sheet1.getRow(1).getCell(j).getStringCellValue();
			hashdata1.put(sheet1.getRow(0).getCell(j).getStringCellValue(), sheet1.getRow(1).getCell(j).getStringCellValue());
			//hashdata1.put((String)Obj[0][j],(String)Obj[1][j]);
			
			}
		Obj[0][0]=hashdata1;
		workbook1.close();
		  
		return Obj;
}
	@SuppressWarnings("null")
	public static Object[][] ExcelReadMultipleRow(String excelName,String sheetName) throws IOException{
		String filePath = excelName;
		File file =    new File(filePath);

		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook1 = new XSSFWorkbook(inputStream);
		//Read sheet inside the workbook by its name

		Sheet sheet1=workbook1.getSheet(sheetName);

		//Find number of rows in excel file
		int rowCount =sheet1.getLastRowNum();
		System.out.println(rowCount);
		Row row = sheet1.getRow(0);

		
		List<HashMap<String,String>> hashdata1= new ArrayList<HashMap<String,String>>();
		//Create a loop to print cell values in a row
		for(int i=0;i<rowCount;i++){
			for (int j = 0; j < row.getLastCellNum(); j++) {
				//hashdata1[i]=new Hashtable<String, String>();
				System.out.println(sheet1.getRow(0).getCell(j).getStringCellValue());
				System.out.println(sheet1.getRow(i+1).getCell(j).getStringCellValue());
				//Obj[0][j]=sheet1.getRow(0).getCell(j).getStringCellValue();
				//Obj[1][j]=sheet1.getRow(1).getCell(j).getStringCellValue();
				HashMap<String, String> hst=new HashMap<String, String>();
				hashdata1.add(hst);
				hashdata1.get(i).put(sheet1.getRow(0).getCell(j).getStringCellValue(), sheet1.getRow(i+1).getCell(j).getStringCellValue());
				//hashdata1.put((String)Obj[0][j],(String)Obj[1][j]);
				
			}
		/*	
			Obj[0][0]=hashdata1.get(i);
			System.out.println(Obj[0][0]);*/
		}
		 Object [][] hashMapObj = new Object [rowCount][1];;
		for(int i=0; i<rowCount ; i++) {
	        hashMapObj[i][0] = hashdata1.get(i);
	    }
		workbook1.close();

		return hashMapObj;
	}

}
