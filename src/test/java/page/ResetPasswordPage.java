package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import setup.UserModel;

import java.util.List;

public class ResetPasswordPage {

    @FindBy(css = "[type=password]")
    List<WebElement> password;

    @FindBy(css = "[type=submit]")
    WebElement btnResetPassword;

    public ResetPasswordPage(WebDriver driver) {

        PageFactory.initElements(driver, this);

    }

    public void resetPassword(UserModel userModel) {

        password.get(0).sendKeys(userModel.getPassword());
        password.get(1).sendKeys(userModel.getPassword());
        btnResetPassword.click();

    }

}
