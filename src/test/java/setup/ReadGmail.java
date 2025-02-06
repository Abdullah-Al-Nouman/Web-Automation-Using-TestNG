package setup;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.configuration.ConfigurationException;
import utils.Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class ReadGmail {

    private Properties properties;

    // Constructor to initialize properties
    public ReadGmail() throws IOException {
        loadEnvironmentVariables();
    }

    // Load environment variables from config file
    private void loadEnvironmentVariables() throws IOException {
        properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("./src/test/resources/config.properties")) {
            properties.load(fileInputStream);
        }
    }

    // Retrieve the list of Gmail messages
    public String getGmailList() throws IOException, ConfigurationException {
        String apiUrl = "/gmail/v1/users/me/messages";
        Response response = sendGetRequest(apiUrl);

        // Extract and return the mail ID from the response
        JsonPath jsonPath = response.jsonPath();
        String mailID = jsonPath.get("messages[0].id");
        System.out.println("Retrieved mail ID: " + mailID);
        return mailID;
    }

    // Fetch Gmail link from the first message and return mail ID
    public String getGmailLink() throws IOException, ConfigurationException {
        return getGmailList();
    }

    // Read the content of an email by its ID
    public String readMailById1() throws IOException, ConfigurationException {
        String mailID = getGmailList();  // Get the mail ID
        String apiUrl = "/gmail/v1/users/me/messages/" + mailID;
        Response response = sendGetRequest(apiUrl);

        // Extract and return the email body (snippet)
        JsonPath jsonPath = response.jsonPath();
        String mailBody = jsonPath.get("snippet");
        System.out.println("Mail Body: " + mailBody);
        return mailBody;
    }

    // Read the content of a specific email by its ID and store reset link in environment variable
    public void readMailById2() throws IOException, ConfigurationException {
        String mailID = getGmailLink();  // Get the mail ID from Gmail
        String apiUrl = "/gmail/v1/users/me/messages/" + mailID;
        Response response = sendGetRequest(apiUrl);

        // Extract the mail body and store it as a reset link in environment variables
        JsonPath jsonPath = response.jsonPath();
        String mailBody = jsonPath.get("snippet");
        System.out.println("Mail Body: " + mailBody);
        Utils.setEnvironmentVariable("reset_link", mailBody);
    }

    // Helper method to send GET request
    private Response sendGetRequest(String apiUrl) {
        RestAssured.baseURI = "https://www.googleapis.com";
        return given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + properties.getProperty("gmail_token"))
                .when()
                .get(apiUrl);
    }
}
