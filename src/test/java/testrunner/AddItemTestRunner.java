package testrunner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.AddItemPage;
import page.LoginPage;
import setup.Setup;
import utils.Utils;

import java.io.FileReader;
import java.io.IOException;

public class AddItemTestRunner extends Setup {

    // Test case 1: User login using registered account
    @Test(priority = 1, description = "Successful login by the user")
    public void userLoginByRegisteredAccount() throws IOException, ParseException {

        // Specify the file path for user credentials
        String filePath = "./src/test/resources/users.json";

        // Initialize login page and JSON parser to read user data
        LoginPage loginPage = new LoginPage(driver);
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(filePath));

        // Get user credentials from the second last and last user in the JSON array
        JSONObject userObj1 = (JSONObject) jsonArray.get(jsonArray.size() - 2);
        String email = userObj1.get("email").toString();

        JSONObject userObj2 = (JSONObject) jsonArray.get(jsonArray.size() - 1);
        String password = userObj2.get("new_password").toString();

        // Perform login with retrieved email and password
        loginPage.doLogin(email, password);

        // Verify successful login by checking the header on the page
        String headerActual = driver.findElement(By.tagName("h2")).getText();
        String headerExpected = "User Daily Costs";
        Assert.assertTrue(headerActual.contains(headerExpected), "Login header not found!");
    }

    // Test case 2: Check if item 1 (Mobile) is added successfully
    @Test(priority = 2, description = "Ensure that item 1 is successfully added")
    public void doAddItems1() throws InterruptedException {

        // Initialize AddItemPage object
        AddItemPage addItemPage = new AddItemPage(driver);

        // Click on the 'Add Cost' button
        driver.findElement(By.className("add-cost-button")).click();

        // Add a Mobile item with price and empty description
        addItemPage.doAddMobile("Mobile", "10000", "");

        // Handle alert after adding the item
        Utils.handleAlert(driver);
    }

    // Test case 3: Check if item 2 (Laptop) is added successfully
    @Test(priority = 3, description = "Ensure that item 2 is successfully added")
    public void doAddItems2() throws InterruptedException {

        // Initialize AddItemPage object
        AddItemPage addItemPage = new AddItemPage(driver);

        // Click on the 'Add Cost' button
        driver.findElement(By.className("add-cost-button")).click();

        // Add a Laptop item with price and description
        addItemPage.doAddLaptop("Laptop", "50000", "Student Laptop");

        // Handle alert after adding the item
        Utils.handleAlert(driver);

        // Wait for a brief moment to ensure the item is added
        Thread.sleep(2000);
    }
}
