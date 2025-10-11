package Base;

import Actions.AssertActions;
import Endpoints.APIConstants;
import Modules.PayloadManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class BaseTest {

    public static RequestSpecification requestSpecification;
    public static Response response;
    public static ValidatableResponse validatableResponse;
    public static AssertActions actions;
    public static PayloadManager payloadManager;
    public static JsonPath jsonPath;


    @BeforeMethod(alwaysRun = true)
    public void setConfig() {
        // Reset the Rest Assured Base URLs
        // Base URL
        // Content Type - ALL

        System.out.println("I am able to run");
        payloadManager = new PayloadManager();
        actions = new AssertActions();
        requestSpecification = RestAssured.given()
                .baseUri(APIConstants.BASE_URL)
                .contentType(ContentType.JSON);

//        requestSpecification = new RequestSpecBuilder()
//                .setBaseUri(APIConstants.BASE_URL)
//                .addHeader("Content-Type", "application/json")
//                .build().log().all();

    }

    public String getToken() throws JsonProcessingException {

        requestSpecification = RestAssured.given().baseUri(APIConstants.BASE_URL).basePath("/auth");
        String payload = payloadManager.setToken();
        response = requestSpecification.contentType(ContentType.JSON)
                .body(payload)
                .when().post();
        jsonPath = new JsonPath(response.asString());
        return jsonPath.getString("token");
    }


}

