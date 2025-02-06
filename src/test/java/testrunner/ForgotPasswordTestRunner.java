package testrunner;

import com.github.javafaker.Faker;
import org.apache.commons.configuration.ConfigurationException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.ForgotPasswordPage;
import setup.ReadGmail;
import setup.Setup;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ForgotPasswordTestRunner extends Setup {

    // Test case 1: User cannot reset password using wrong email
    @Test(priority = 1, description = "Verify that the user cannot reset the password with an incorrect email")
    public void resetPasswordUsingWrongEmail() throws InterruptedException {

        // Initialize ForgotPasswordPage object
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);

        // Generate a fake email using Faker library
        Faker faker = new Faker();
        List<WebElement> tagNameElement = driver.findElements(By.tagName("a"));
        tagNameElement.get(0).click(); // Click on the 'Forgot Password' link

        // Attempt to reset password using a randomly generated wrong email
        forgotPasswordPage.doResetPassword(faker.name().firstName().toLowerCase() + "@gmail.com");

        // Wait for the response
        Thread.sleep(1000);

        // Capture the warning message and verify the expected warning text
        String warningActual = driver.findElement(By.tagName("p")).getText();
        String warningExpected = "Your email is not registered";
        Assert.assertTrue(warningActual.contains(warningExpected), "Expected warning message not displayed");

        // Refresh the page to reset the state
        driver.navigate().refresh();
        Thread.sleep(2000);
    }

    // Test case 2: User cannot reset password using invalid email
    @Test(priority = 2, description = "Ensure the password reset is not allowed with an invalid email")
    public void resetPasswordUsingInvalidEmail() throws InterruptedException {

        // Initialize ForgotPasswordPage object
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);

        // Generate a fake invalid email using Faker
        Faker faker = new Faker();
        forgotPasswordPage.doResetPassword(faker.name().firstName().toLowerCase() + "@gmail");

        // Wait for the response
        Thread.sleep(2000);

        // Capture the warning message and verify the expected warning text
        String warningActual = driver.findElement(By.tagName("p")).getText();
        String warningExpected = "Your email is not registered";
        Assert.assertTrue(warningActual.contains(warningExpected), "Expected warning message not displayed");

        // Refresh the page to reset the state
        driver.navigate().refresh();
        Thread.sleep(2000);
    }

    // Test case 3: User can reset password using valid email
    @Test(priority = 3, description = "Verify that the user can reset their password with a valid email")
    public void resetPasswordUsingValidEmail() throws IOException, ParseException, ConfigurationException, InterruptedException {

        // Specify the file path for the user data
        String filePath = "./src/test/resources/users.json";

        // Initialize ForgotPasswordPage object and JSON parser to read user data
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(filePath));
        JSONObject userObj = (JSONObject) jsonArray.get(jsonArray.size() - 1);

        // Retrieve the valid email for the registered user
        String email = userObj.get("email").toString();
        forgotPasswordPage.doResetPassword(email);

        // Capture the message displayed after attempting password reset
        String messageActual = driver.findElement(By.tagName("p")).getText();
        String messageExpected = "Password reset link sent to your email";
        Assert.assertTrue(messageActual.contains(messageExpected), "Password reset message not displayed");

        // Wait for the email to arrive
        Thread.sleep(5000);

        // Read the Gmail inbox to find the reset link
        ReadGmail readGmail = new ReadGmail();
        readGmail.getGmailLink(); // Retrieve the latest email ID

        // Read the reset email and retrieve the reset link
        readGmail.readMailById2();
    }
}
