package testrunner;

import com.github.javafaker.Faker;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import page.ResetPasswordPage;
import setup.Setup;
import setup.UserModel;
import utils.Utils;

import java.io.IOException;

public class ResetPasswordTestRunner extends Setup {

    // Test case to verify if the user can reset their password
    @Test(description = "Ensure if user can reset password")
    public void resetPassword() throws IOException, ParseException, InterruptedException {

        // Instantiate Utils class to get the reset password link
        Utils utils = new Utils();
        String resetLink = utils.getResetLink();  // Retrieve the reset link dynamically

        // Navigate to the password reset page using the retrieved link
        driver.get(resetLink);

        // Instantiate the ResetPasswordPage and UserModel to update the password
        ResetPasswordPage resetPasswordPage = new ResetPasswordPage(driver);
        UserModel userModel = new UserModel();
        userModel.setPassword("SQAb13%#1");  // Set the new password for the user

        // Call the resetPassword method to update the password on the UI
        resetPasswordPage.resetPassword(userModel);

        // Save the new password for future reference using a JSON object
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("new_password", userModel.getPassword());
        Utils.saveData(jsonObject);  // Save the updated password in a file

        // Wait for the process to complete before ending the test case
        Thread.sleep(2000);
    }

}
