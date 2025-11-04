package com.LambdaTest.testScript;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.LambdaTest.base.BaseClass;
import com.LambdaTest.utilities.ReadExcelFile;


public class TestScript1 extends  BaseClass {
	
	@DataProvider(name = "formData")
    public Object[][] getFormData() throws IOException {
        // Create instance of ExcelReader and provide the Excel file path
		ReadExcelFile excel = new ReadExcelFile();
        excel.setSheetByName("Sheet1"); // Set the sheet name where your data is stored
       
        int rowCount = excel.getRowCount();
        int colCount = excel.getColumnCount(0);
        

        // Create a 2D array to store data from Excel
        Object[][] data = new Object[rowCount - 1][colCount]; // -1 to skip header row

        // Loop through the Excel sheet and populate the data array
        for (int i = 1; i < rowCount; i++) {  // Start from row 1 to skip header row
            for (int j = 0; j < colCount; j++) {
                data[i - 1][j] = excel.getCellData(i, j);
            	 
            }
        }
        
        excel.closeWorkbook();
        
        return data; // Return the 2D array containing test data
    }
	
	 @Test (priority =0)
	  public void VerifyPageTitle() {
		 
		  
		  Assert.assertEquals("Selenium Grid Online | Run Selenium Test On Cloud", loginPageMethods.getPageTitle());
		  
	  }
	 
	 @Test (priority =1)
	  public void VerifyPageHeading() {
		 
		  
		  Assert.assertEquals("Form Demo", loginPageMethods.getPageHeading());
		  
	  }
	 
	 @Test (priority =3)
	  public void VerifyFormHeading() {
		 
		  
		  Assert.assertEquals("Input form validations", loginPageMethods.getFormHeading());
		  
	  }
	
  @Test (dataProvider = "formData", priority =4)
  public void VerifySubmitForm(String Name,String Email,String Password,String Company,String WebSite,String City,String Address1,String Address2,String State,String ZipCode) throws InterruptedException {
	  
	 
	  loginPageMethods.submitForm(Name,Email,Password, Company, WebSite, City, Address1, Address2, State, ZipCode);
	 
	  
	  
	  
  }
}
