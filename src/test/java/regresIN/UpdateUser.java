package regresIN;
import static  io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static  org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.annotations.Test;

public class UpdateUser {
    @Test
    public void updateUserTest() {
        JSONObject obj = new JSONObject();
        obj.put("name","morpheus");
        obj.put("job","zion resident");

        given().header("Content-Type","application/json").body(obj.toString()).put("https://reqres.in/api/users/2").
                then().statusCode(200).log().all();
    }
    @Test
    public void updateUserV2Test() {
        JSONObject obj = new JSONObject();
        obj.put("name","morpheus");
        obj.put("job","zion resident");

        given().header("Content-Type","application/json").body(obj.toString()).patch("https://reqres.in/api/users/2").
                then().statusCode(200).body("name",equalTo("morpheus")).log().all();


    }
}
