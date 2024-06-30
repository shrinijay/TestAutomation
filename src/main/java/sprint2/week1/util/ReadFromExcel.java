package sprint2.week1.util;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;

public class ReadFromExcel {

    public static Object[][] readDataFromExcel(String filename) {

        try {

            XSSFWorkbook workbook = new XSSFWorkbook(new File("./data/" + filename + ".xlsx"));
            XSSFSheet sheet = workbook.getSheetAt(0);
            int rowCount = sheet.getLastRowNum();
            System.out.println("Last row num "+rowCount);
            int columnCount = sheet.getRow(0).getLastCellNum();

            String[][] dataArr = new String[rowCount][columnCount];

            for (int i = 1; i <rowCount+1; i++) {
                for (int j = 0; j < columnCount; j++) {
                    String value = sheet.getRow(i).getCell(j).getStringCellValue();
                    dataArr[i-1][j] = value;
                    System.out.println("from ReadExcel --- " + value);
                }
            }

            return dataArr;
        }
        catch(Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }


    }

}
