package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LoginPage {

    // Input field for email
    @FindBy(id = "email")
    WebElement txtEmail;

    // Input field for password
    @FindBy(id = "password")
    WebElement txtPassword;

    // Login button
    @FindBy(css = "[type=submit]")
    WebElement btnLogin;

    // List of menu items available in the UI
    @FindBy(css = "[role=menuitem]")
    public List<WebElement> menuRole;

    WebDriver driver;

    // Constructor to initialize WebElements using PageFactory
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Method to perform user login.
     * @param email - Registered user email
     * @param password - User password
     */
    public void doLogin(String email, String password) {
        txtEmail.sendKeys(email); // Enter email
        txtPassword.sendKeys(password); // Enter password
        btnLogin.click(); // Click the login button
    }

    /**
     * Method to perform user logout.
     */
    public void doLogout() {
        // Initialize user profile page
        UserProfilePage userProfilePage = new UserProfilePage(driver);

        // Click on the profile button and select "Logout" from the menu
        userProfilePage.btnElements.get(0).click();
        menuRole.get(1).click();
    }
}
