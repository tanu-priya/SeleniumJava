package com.inferenceCloud.dataproviders;

import org.testng.annotations.DataProvider;

import com.inferenceCloud.utils.ExcelUtil;

public class TestDataFromExcel {

    @DataProvider(name = "excelData")
    public Object[][] getDataFromExcel() {
        // Implementation to read data from Excel and return as Object[][]
        String path = System.getProperty("user.dir")
                + "/src/test/resources/testdata/LoginData.xlsx";

        return ExcelUtil.getTestData("Login", path);
    }
    
}
