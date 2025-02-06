package testrunner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.LoginPage;
import setup.Setup;

import java.io.FileReader;
import java.io.IOException;

public class LoginByUpdatedEmailTestRunner extends Setup {

    // Test case to check if user cannot login using old email
    @Test(priority = 1, description = "Check if user cannot login using old email")
    public void doLoginByOldEmail() throws IOException, ParseException {
        String filePath = "./src/test/resources/users.json";

        // Load user data from the JSON file
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(filePath));
        JSONObject userObj1 = (JSONObject) jsonArray.get(jsonArray.size() - 3);
        String email = userObj1.get("email").toString();

        JSONObject userObj2 = (JSONObject) jsonArray.get(jsonArray.size() - 2);
        String password = userObj2.get("new_password").toString();

        // Perform login using the old email
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(email, password);

        // Validate if the login fails with appropriate error message
        String validationErrorActual = driver.findElement(By.tagName("p")).getText();
        String validationErrorExpected = "Invalid email or password";
        Assert.assertTrue(validationErrorActual.contains(validationErrorExpected));

        driver.navigate().refresh();
    }

    // Test case to check if user can login using updated email
    @Test(priority = 2, description = "Verify that the user can successfully log in with their updated email")
    public void doLoginByUpdatedEmail() throws IOException, ParseException {
        String filePath = "./src/test/resources/users.json";

        // Load user data from the JSON file
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(filePath));
        JSONObject userObj1 = (JSONObject) jsonArray.get(jsonArray.size() - 1);
        String email = userObj1.get("updated_email").toString();

        JSONObject userObj2 = (JSONObject) jsonArray.get(jsonArray.size() - 2);
        String password = userObj2.get("new_password").toString();

        // Perform login using the updated email
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(email, password);

        // Validate if the login is successful
        String headerActual = driver.findElement(By.tagName("h2")).getText();
        String headerExpected = "User Daily Costs";
        Assert.assertTrue(headerActual.contains(headerExpected));

        // Logout after successful login
        loginPage.doLogout();
    }
}
