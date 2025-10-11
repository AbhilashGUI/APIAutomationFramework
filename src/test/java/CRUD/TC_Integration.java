package CRUD;

import Base.BaseTest;
import Endpoints.APIConstants;
import Payloads.Booking;
import Payloads.BookingResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


    public class TC_Integration extends BaseTest {
        String token;
        String bookingId;
        String bookingIdPojo;

        // Create a Booking
        // Update the Booking with Token and Booking ID - How to pass the variales from one test to another.
        // 1. Auth - API Key
        // Cookie Based Auth Side.
        // OAuth 2.0 - Method how you can use the OAuth 2.0
        // Delete Also



        // Get a Token - Extract the Token

        private static final Logger log = LogManager.getLogger(TC_Integration.class);


        // Create a Booking
        @Test(groups = "P0")
        public void testCreateBooking() throws JsonProcessingException {
            token = getToken();
            assertThat(token).isNotNull().isNotEmpty();

            requestSpecification.basePath(APIConstants.CREATE_BOOKING);
            response = RestAssured.given().spec(requestSpecification)
                    .when().body(payloadManager.createPayload()).post();
            validatableResponse = response.then().log().all();
            jsonPath = JsonPath.from(response.asString());
            validatableResponse.statusCode(200);
            // Direct Extraction from json Path
            bookingId = jsonPath.getString("bookingid");
            // Booking Response Class
            BookingResponse bookingResponse = payloadManager.JsonToObject(response.asString());
            bookingIdPojo = bookingResponse.getBookingid().toString();
            log.info("This is My Booking ID"+bookingIdPojo);
            assertThat(bookingId).isNotNull().isNotEmpty();

        }


        //Update the Booking with Token and Booking ID
        @Test(groups = "P0", dependsOnMethods = {"testCreateBooking"})
        public void testUpdateBooking() throws JsonProcessingException {
            requestSpecification.basePath(APIConstants.UPDATE_BOOKING + "/" + bookingId);
            response = RestAssured.given().spec(requestSpecification).cookie("token",token)
                    .when().body(payloadManager.updatedPayload()).put();
            validatableResponse = response.then().log().all();
            //validatableResponse.body("firstname", Matchers.is("Lucky"));

            Booking bookingResponse = payloadManager.JsonToObjectPUT(response.asString());
            assertThat(bookingResponse.getFirstname()).isEqualTo("Lucky").isNotNull();
            assertThat(bookingResponse.getLastname()).isNotNull();
            assertThat(bookingResponse.getDepositpaid()).isNotNull();
            assertThat(bookingResponse.getBookingdates().getCheckin()).isNotNull();
            assertThat(bookingResponse.getBookingdates().getCheckout()).isNotEmpty();

        }

        // Delete Also
        @Test(groups = "P0",dependsOnMethods = { "testUpdateBooking"})
        public void testDelete_CreatedBooking(){
            requestSpecification.basePath(APIConstants.UPDATE_BOOKING + "/" + bookingId).cookie("token",token);
            ValidatableResponse validatableResponse = RestAssured.given().spec(requestSpecification).auth().basic("admin", "password123")
                    .when().delete().then().log().all();
            validatableResponse.statusCode(201);
        }

        @Test(groups = "P0",dependsOnMethods = { "testDelete_CreatedBooking"})
        public void testDeleteBooking_ByGet(){
            requestSpecification.basePath(APIConstants.UPDATE_BOOKING+"/"+bookingId);
            response = RestAssured.given().spec(requestSpecification)
                    .when().get();
            validatableResponse = response.then().log().all();
            validatableResponse.statusCode(404);
        }


    }

