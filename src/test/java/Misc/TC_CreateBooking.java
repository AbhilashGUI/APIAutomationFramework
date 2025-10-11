package Misc;

import Actions.AssertActions;
import Base.BaseTest;
import Endpoints.APIConstants;
import Modules.PayloadManager;
import Payloads.BookingResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


    public class TC_CreateBooking extends BaseTest {


        @Test(groups = {"stage","P0"})
        @Owner("Pramod")
        @Severity(SeverityLevel.CRITICAL)
        @Description("TC#1 - Verify that Create Booking works and ID is generated!")
        public void testCreateBooking() throws JsonProcessingException {
            // Call the Request Block
            // Call the Payload
            // Call the Assertion Block
            payloadManager = new PayloadManager();
            actions = new AssertActions();
            requestSpecification = RestAssured.given()
                    .basePath(APIConstants.CREATE_BOOKING)
                    .contentType(ContentType.JSON).log().all();
            response = requestSpecification.when().body(payloadManager.createPayload()).post();
            validatableResponse = response.then().log().all();
            validatableResponse.statusCode(200);
            BookingResponse bookingResponse = payloadManager.JsonToObject(response.asString());
            assertThat(bookingResponse.getBookingid().toString()).isNotEmpty().isNotNull();
        }

        @Test(groups = {"stage","P0"})
        @Description("TC#2 -  Verify that Create Booking with No payload")
        public void testCreateBooking2_Negative() throws JsonProcessingException {
            payloadManager = new PayloadManager();
            actions = new AssertActions();
            requestSpecification = RestAssured.given()
                    .basePath(APIConstants.CREATE_BOOKING)
                    .contentType(ContentType.JSON).log().all();
            response = requestSpecification.when().body("").post();
            validatableResponse = response.then().log().all();
            validatableResponse.statusCode(500);

        }

    }

