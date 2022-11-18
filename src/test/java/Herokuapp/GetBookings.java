package Herokuapp;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetBookings {
    @Test
    public void getBookingsTest() {
        given().get("https://restful-booker.herokuapp.com/booking/4").then().log().all();
    }
}
