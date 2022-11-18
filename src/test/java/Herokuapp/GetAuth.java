package Herokuapp;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static  io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static  org.hamcrest.Matchers.*;
public class GetAuth {
    static String token ="";
    @Test
    public void getAuthTest() {
        JSONObject params = new JSONObject();
        params.put("username","admin");
        params.put("password","password123");
        Response response = given().header("Content-Type","application/json").when().body(params.toString())
                .post("https://restful-booker.herokuapp.com/auth");
        System.out.println( response.jsonPath().get("token").toString());
        token = response.jsonPath().get("token").toString();
    }

}
