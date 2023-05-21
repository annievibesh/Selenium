
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReadExample {

    public static void main(String[] args) {
        
        

        // Create a new instance of the ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Open the webpage
        driver.get("https://www.saucedemo.com/");

        try {
            // Read Excel file
            FileInputStream file = new FileInputStream("FirstSelinium/excel/file.xlsx");
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);

            // Loop through each row in the Excel sheet
            for (Row row : sheet) {
                // Get the username and password from the cells in the current row
                String username = row.getCell(0).getStringCellValue();
                String password = row.getCell(1).getStringCellValue();

                // Find the username and password input fields on the webpage
                WebElement usernameField = driver.findElement(By.id("username"));
                WebElement passwordField = driver.findElement(By.id("password"));

                // Enter the username and password values
                usernameField.sendKeys(username);
                passwordField.sendKeys(password);

                // Submit the form or perform any necessary actions
                // ...

                // Clear the input fields for the next set of credentials
                usernameField.clear();
                passwordField.clear();
            }

            // Close the Excel file
            workbook.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Close the browser
        driver.quit();
    }
}

