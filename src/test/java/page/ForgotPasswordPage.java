package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ForgotPasswordPage {

    // Input field for entering the registered email
    @FindBy(css = "[type=email]")
    WebElement txtForgotEmail;

    // Button to submit the password reset request
    @FindBy(css = "[type=submit]")
    WebElement btnReset;

    // Constructor to initialize WebElements using PageFactory
    public ForgotPasswordPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    /**
     * Method to trigger the password reset process.
     * @param email - Registered email address for password recovery
     */
    public void doResetPassword(String email) {
        txtForgotEmail.sendKeys(email);
        btnReset.click();
    }
}
