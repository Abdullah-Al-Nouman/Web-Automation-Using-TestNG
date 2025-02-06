package setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class Setup {

    // Declare the WebDriver and Properties objects
    public WebDriver driver;
    Properties properties;
    FileInputStream fileInputStream;

    // Setup method runs before each test, initializes the WebDriver, loads properties, and opens the application
    @BeforeTest
    public void setup() throws IOException {

        // Initialize ChromeDriver, maximize the window, and set implicit wait timeout
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        // Open the specified URL in the browser
        driver.get("https://dailyfinance.roadtocareer.net/");

        // Load configuration properties
        properties = new Properties();
        fileInputStream = new FileInputStream("./src/test/resources/config.properties");
        properties.load(fileInputStream);

    }

    // Quit the browser after each test
    @AfterTest
    public void quitBrowser() {
        driver.quit(); // Close the browser window and clean up resources
    }
}
