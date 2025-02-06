package testrunner;

import com.github.javafaker.Faker;
import org.apache.commons.configuration.ConfigurationException;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.RegistrationPage;
import setup.ReadGmail;
import setup.Setup;
import setup.UserModel;
import utils.Utils;

import java.io.IOException;

public class RegistrationTestRunner extends Setup {

    // Test case to verify if the user registration is successful
    @Test(description = "Verify if registration is successful")
    public void userRegistration() throws InterruptedException, IOException, ParseException, ConfigurationException {

        // Initialize the RegistrationPage and Faker for generating random data
        RegistrationPage registrationPage = new RegistrationPage(driver);
        Faker faker = new Faker();

        // Create a new UserModel and set properties using Faker
        UserModel userModel = new UserModel();
        driver.findElement(By.partialLinkText("Register")).click();
        userModel.setFirstname(faker.name().firstName());
        userModel.setLastname(faker.name().lastName());
        userModel.setEmail("alnouman172012+" + Utils.generateRandomID(1, 999) + "@gmail.com");
        userModel.setPassword("1234");
        userModel.setPhonenumber("0190" + Utils.generateRandomID(1000000, 9999999));
        userModel.setAddress(faker.address().city());

        // Perform registration using the generated user data
        registrationPage.doRegister(userModel);
        Thread.sleep(3000);

        // Assert that a success message is displayed after registration
        String successMessage = driver.findElement(By.className("Toastify__toast")).getText();
        Assert.assertTrue(successMessage.contains("registered successfully"));

        // Create a JSON object to save user details for further use
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("firstName", userModel.getFirstname());
        jsonObject.put("lastName", userModel.getLastname());
        jsonObject.put("email", userModel.getEmail());
        jsonObject.put("password", userModel.getPassword());
        jsonObject.put("phoneNumber", userModel.getPhonenumber());
        jsonObject.put("address", userModel.getAddress());

        // Save the data to a file using Utils
        Utils.saveData(jsonObject);
        Thread.sleep(3000);

        // Use ReadGmail class to fetch the registration email
        ReadGmail readGmail = new ReadGmail();
        Thread.sleep(1000);
        String mailBody = readGmail.readMailById1(); // Retrieve the first email received

        // Assert that the registration confirmation email contains the expected message
        Assert.assertTrue(mailBody.contains("Welcome to our platform!"));
    }

}
