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

public class LoginTestRunner extends Setup {

    // Test case to check if user can login with the new password
    @Test(description = "Check if user can login with new password")
    public void userLoginByRegisteredAccount() throws IOException, ParseException {


        String filePath = "./src/test/resources/users.json";

        // Parse JSON file to extract user credentials
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(filePath));

        // Retrieve the registered user's email and new password
        JSONObject userObj1 = (JSONObject) jsonArray.get(jsonArray.size() - 2);
        String email = userObj1.get("email").toString();

        JSONObject userObj2 = (JSONObject) jsonArray.get(jsonArray.size() - 1);
        String password = userObj2.get("new_password").toString();

        // Perform login with the extracted email and new password
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(email, password);

        // Verify if the login was successful by checking the page header
        String headerActual = driver.findElement(By.tagName("h2")).getText();
        String headerExpected = "User Daily Costs";
        Assert.assertTrue(headerActual.contains(headerExpected), "Login was not successful.");
    }
}
