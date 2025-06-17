package api.utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name = "datafor_UserModule")
	public String[][] readExcelData() throws EncryptedDocumentException, IOException {
		FileInputStream file = new FileInputStream("./testData/RestApiData.xlsx");

		Workbook wb = WorkbookFactory.create(file);
		Sheet sheet = wb.getSheet("sheet1");
		int total_rownumber = sheet.getPhysicalNumberOfRows();
		int total_cellnumber = sheet.getRow(0).getPhysicalNumberOfCells();

		String[][] Apiarray = new String[total_rownumber - 1][total_cellnumber];

		for (int i = 1; i < total_rownumber; i++) // rows
		{
			for (int c = 0; c < total_cellnumber; c++) // column
			{
				// Object Data=sheet.getRow(i).getCell(c).getStringCellValue();

				// Apiarray[i-1][c]=Data;

				Cell cell = sheet.getRow(i).getCell(c);
				String data1 = cell.toString();
				Apiarray[i - 1][c] = data1;

			}

		}

		return Apiarray;
	}

//	//@Test
//    public void getArray() {
//		
//		System.out.println(Arrays.toString(Apiarray));
//		for(Object [] a1:Apiarray) 
//		{
//			System.out.println(Arrays.toString(a1));
//		}
//	
//}
}
