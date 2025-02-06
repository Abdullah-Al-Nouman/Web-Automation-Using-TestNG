package testrunner;

import com.github.javafaker.Faker;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.LoginPage;
import page.UserProfilePage;
import setup.Setup;
import utils.Utils;

import java.io.FileReader;
import java.io.IOException;

public class UserProfileTestRunner extends Setup {

    @Test(priority = 1, description = "User successfully logs into the system")
    public void userLoginByRegisteredAccount() throws IOException, ParseException {
        // Load user data from the JSON file
        String filePath = "./src/test/resources/users.json";
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(filePath));

        // Retrieve credentials for login
        JSONObject userObj1 = (JSONObject) jsonArray.get(jsonArray.size() - 2);
        String email = userObj1.get("email").toString();

        JSONObject userObj2 = (JSONObject) jsonArray.get(jsonArray.size() - 1);
        String password = userObj2.get("new_password").toString();

        // Perform login operation
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(email, password);

        // Validate login by checking the header text
        String headerActual = driver.findElement(By.tagName("h2")).getText();
        String headerExpected = "User Daily Costs";
        Assert.assertTrue(headerActual.contains(headerExpected));
    }

    @Test(priority = 2, description = "Verify if user can update email")
    public void doUpdateEmail() throws InterruptedException, IOException, ParseException {
        // Initialize the UserProfilePage for interaction
        UserProfilePage userProfilePage = new UserProfilePage(driver);

        // Navigate to the profile page and open the email update section
        userProfilePage.btnElements.get(0).click();
        userProfilePage.menuRole.get(0).click();

        // Generate a new email and update it
        String newEmail = "alnouman172012+" + Utils.generateRandomID(101, 999) + "@gmail.com";
        userProfilePage.updateEmail(newEmail);

        // Handle the confirmation alert
        Utils.handleAlert(driver);

        // Close the profile section and re-open it
        userProfilePage.btnElements.get(0).click();
        userProfilePage.menuRole.get(1).click();

        // Load the users data and update the email entry
        String filePath = "./src/test/resources/users.json";
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(filePath));

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("updated_email", newEmail);

        // Add the new email to the JSON array and save it
        jsonArray.add(jsonObject);
        Utils.saveData(jsonObject);
    }
}
