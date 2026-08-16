package Modules;

import Payloads.Auth;
import Payloads.Booking;
import Payloads.BookingResponse;
import Payloads.Bookingdates;
import Utils.FakerUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class PayloadManager {



        private final ObjectMapper objectMapper= new ObjectMapper();

        public String createBookingPayload() throws JsonProcessingException {
            Booking booking = new Booking();
            booking.setFirstname(FakerUtil.getUserName());
            booking.setLastname("Vignesh");
            booking.setTotalprice(123);
            booking.setDepositpaid(true);
            booking.setAdditionalneeds("BreakFast");

            Bookingdates bookingdates = new Bookingdates();
            bookingdates.setCheckin("2022-01-01");
            bookingdates.setCheckout("2022-01-10");
            booking.setBookingdates(bookingdates);

            String payload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking);
            return payload;

        }

        public String createNegativeBookingPayload() throws JsonProcessingException {
            Booking booking = new Booking();
            booking.setFirstname(FakerUtil.getUserName());
            booking.setLastname("");
            booking.setTotalprice(123);
            booking.setDepositpaid(false);
            booking.setAdditionalneeds("BreakFast");

            Bookingdates bookingdates = new Bookingdates();
            bookingdates.setCheckin("2022-01-01");
            bookingdates.setCheckout("2022-01-10");
            booking.setBookingdates(bookingdates);

            String payload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking);
            return payload;

        }

        public BookingResponse JsonToBookingResponse(String jsonString) throws JsonProcessingException {

            BookingResponse bookingResponse = objectMapper.readValue(jsonString, BookingResponse.class);
            return bookingResponse;
        }

        public Booking JsonToBooking(String jsonString) throws JsonProcessingException {

            Booking bookingResponse = objectMapper.readValue(jsonString, Booking.class);
            return bookingResponse;
        }

        public String createUpdatedBookingPayload() throws JsonProcessingException {

            Booking booking = new Booking();
            booking.setFirstname("Lucky");
            booking.setLastname("Dutta");
            booking.setTotalprice(199);
            booking.setDepositpaid(true);
            booking.setAdditionalneeds("Breakfast, lunch");
            Bookingdates bookingdates = new Bookingdates();
            bookingdates.setCheckin("2022-10-01");
            bookingdates.setCheckout("2022-10-01");
            booking.setBookingdates(bookingdates);
            String payload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking);
            return payload;
        }

        public String createPatchpayload() throws JsonProcessingException {

            Booking patchbooking = new Booking();
            patchbooking.setFirstname("Abhilash");

            String payload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(patchbooking);
            return payload;
        }

        /***public String updatePayload(){
            return null;
        }***/


        public String createAuthpayload() throws JsonProcessingException {
            Auth auth = new Auth();
            auth.setUsername("admin");
            auth.setPassword("password123");
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(auth);

        }

    }


