package testrunner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.LoginPage;
import setup.Setup;
import utils.Utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class AdminLoginTestRunner extends Setup {

    // Test case 1: Admin login functionality
    @Test(priority = 1, description = "Ensure that the admin can successfully log in")
    public void doAdminLogin() {

        // Initialize the LoginPage object and login using credentials from system properties
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(System.getProperty("email"), System.getProperty("password"));

        // Validate successful login by checking the header and total count visibility
        String headerActual = driver.findElement(By.tagName("h2")).getText();
        String headerExpected = "Admin Dashboard";
        Assert.assertTrue(headerActual.contains(headerExpected), "Header does not contain 'Admin Dashboard'.");

        // Check if the total count element is displayed on the dashboard
        Assert.assertTrue(driver.findElement(By.className("total-count")).isDisplayed(), "Total count element is not displayed.");
    }

    // Test case 2: Admin search functionality by updated email
    @Test(priority = 2, description = "Check if admin can search user by updated email")
    public void searchByUpdatedEmail() throws IOException, ParseException, InterruptedException {

        // Specify the file path and parse the user data
        String filePath = "./src/test/resources/users.json";
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(filePath));

        // Retrieve the updated email for search
        JSONObject userObj = (JSONObject) jsonArray.get(jsonArray.size() - 1);
        String updatedEmail = userObj.get("updated_email").toString();

        // Find the search box and input the updated email
        WebElement searchBox = driver.findElement(By.className("search-box"));
        searchBox.sendKeys(updatedEmail);

        // Add a small delay to ensure search results load
        Thread.sleep(2000);

        // Retrieve all email cells in the table and get the actual email from the third cell
        List<WebElement> displayedEmail = driver.findElements(By.tagName("td"));
        String actualEmail = displayedEmail.get(2).getText();

        // Verify if the email contains the updated email (or specific part of it)
        Assert.assertTrue(actualEmail.contains("alnouman172012"), "The updated email is not displayed correctly.");

        // Logout after search operation
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogout();
    }
}
