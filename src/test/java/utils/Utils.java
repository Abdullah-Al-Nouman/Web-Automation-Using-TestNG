package utils;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class Utils {

    // Method to handle alert pop-ups in Selenium WebDriver
    public static void handleAlert(WebDriver driver) throws InterruptedException {
        Thread.sleep(1000);
        driver.switchTo().alert().accept();  // Accept the alert
        Thread.sleep(1000);
    }

    // Method to generate a random ID between a given range
    public static int generateRandomID(int min, int max) {
        double randomID = Math.random() * (max - min) + min;  // Generate random number
        return (int) randomID;
    }

    // Method to save user data into the JSON file
    public static void saveData(JSONObject jsonObject) throws IOException, ParseException {
        String filePath = "./src/test/resources/users.json";  // Path to the users JSON file

        JSONParser jsonParser = new JSONParser();
        // Parse the existing data in the JSON file
        JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader(filePath));

        // Add the new user data to the JSON array
        jsonArray.add(jsonObject);

        // Write the updated JSON array back to the file
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(jsonArray.toJSONString());
            writer.flush();
        }
    }

    // Method to set environment variable in the config properties file
    public static void setEnvironmentVariable(String key, String value) throws ConfigurationException {
        PropertiesConfiguration configuration = new PropertiesConfiguration("./src/test/resources/config.properties");
        configuration.setProperty(key, value);  // Set the property
        configuration.save();  // Save the updated properties file
    }

    // Method to get the reset link from the config properties file
    public String getResetLink() {
        Properties properties = new Properties();
        String link = "";

        try (FileInputStream fileInputStream = new FileInputStream("./src/test/resources/config.properties")) {
            properties.load(fileInputStream);  // Load properties from file

            // Retrieve the reset link from the properties file
            String resetLink = properties.getProperty("reset_link");
            link = extractLink(resetLink);  // Extract the link from the text

        } catch (IOException e) {
            // Handle any IO exceptions (could log this for debugging)
        }

        return link;  // Return the extracted link
    }

    // Method to extract a URL from a text string
    public static String extractLink(String text) {
        String link = "";
        int startIndex = text.indexOf("http");  // Find the start of the URL
        if (startIndex != -1) {
            int endIndex = text.indexOf(" ", startIndex);  // Find the end of the URL
            if (endIndex == -1) {
                endIndex = text.length();  // If no space is found, take till the end of the string
            }
            link = text.substring(startIndex, endIndex);  // Extract the URL
        }
        return link;  // Return the extracted URL
    }
}
