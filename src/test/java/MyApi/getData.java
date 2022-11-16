package MyApi;
import org.testng.annotations.Test;

import static  io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static  org.hamcrest.Matchers.*;

public class getData extends BaseData {
    @Test
    public void getAllUsersTest() {

        String items[] = {"Mercer","Johnson"};
        given().get("/users").then().statusCode(200).statusLine("HTTP/1.1 200 OK")
                .body("lastName",contains(items)).body("[0].name",equalTo("Alex")).log().all();
    }
    @Test
    public void getSingleUser() {
        given().header("Content-Type","application/json").get("/users/1").then().statusCode(200)
                .body("name",equalTo("Alex")).log().all();

    }

}
