package page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class UserProfilePage {

    // List of buttons present in the user profile section
    @FindBy(css = "[type=button]")
    public List<WebElement> btnElements;

    // Email input field
    @FindBy(css = "[type=email]")
    WebElement txtEmail;

    // List of menu items available in the profile dropdown
    @FindBy(css = "[role=menuitem]")
    public List<WebElement> menuRole;

    // Constructor to initialize WebElements using PageFactory
    public UserProfilePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    // Method to update the user's email address.

    public void updateEmail(String newEmail) {
        btnElements.get(1).click(); // Click the edit button
        txtEmail.sendKeys(Keys.CONTROL, "a", Keys.BACK_SPACE); // Clear existing email
        txtEmail.sendKeys(newEmail); // Enter the new email
        btnElements.get(2).click(); // Click the save button
    }
}
