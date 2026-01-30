package com.inferenceCloud.utils;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

    public static Object[][] getTestData(String sheetName, String filePath) {
        Object[][] data = null;

        try{
            try(FileInputStream fis = new FileInputStream(filePath);
                Workbook workbook = new XSSFWorkbook(fis)) {
                Sheet sheet = workbook.getSheet(sheetName);
                int rowCount = sheet.getPhysicalNumberOfRows();
                int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
                data = new Object[rowCount - 1][colCount];
                DataFormatter formatter = new DataFormatter();

                for(int i = 1; i < rowCount; i++) {
                    Row row = sheet.getRow(i);

                    for(int j = 0; j < colCount; j++) {
                        Cell cell = (row == null) ? null : row.getCell(j);
                        data[i - 1][j] = (cell == null) ? "" : formatter.formatCellValue(cell);
                    }
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return data;
    }
    
}
