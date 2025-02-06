package page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class AddItemPage {

    // Input field for item name
    @FindBy(id = "itemName")
    WebElement txtItemName;

    // List of buttons available on the page
    @FindBy(css = "[type=button]")
    List<WebElement> btnButton;

    // Input field for amount
    @FindBy(id = "amount")
    WebElement txtAmount;

    // Dropdown for selecting purchase date
    @FindBy(id = "purchaseDate")
    WebElement selectPurchaseDate;

    // Dropdown for selecting the month
    @FindBy(id = "month")
    WebElement clickMonth;

    // Input field for remarks
    @FindBy(id = "remarks")
    WebElement txtRemarks;

    // Submit button for adding an item
    @FindBy(className = "submit-button")
    WebElement btnSubmit;

    // Constructor to initialize WebElements using PageFactory
    public AddItemPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    // Method to add a mobile item with required details.

    public void doAddMobile(String itemName, String amount, String remarks) {
        txtItemName.sendKeys(itemName);
        btnButton.get(2).click(); // Clicks a specific button, assumed to be category selection
        txtAmount.sendKeys(amount);

        // Selecting January from the month dropdown
        Select selectMonth = new Select(clickMonth);
        selectMonth.selectByVisibleText("January");

        txtRemarks.sendKeys(remarks);
        btnSubmit.click();
    }


    public void doAddLaptop(String itemName, String amount, String remarks) {
        txtItemName.sendKeys(itemName);

        // Commented out button click, assuming it's unnecessary for laptop entry
        // btnButton.get(2).click();

        txtAmount.sendKeys(amount);

        // Selecting February from the month dropdown
        Select selectMonth = new Select(clickMonth);
        selectMonth.selectByVisibleText("February");

        txtRemarks.sendKeys(remarks);
        btnSubmit.click();
    }
}
