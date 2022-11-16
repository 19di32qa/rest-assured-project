package regresIN;
import static  io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static  org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.annotations.Test;

public class DeleteUser {
    @Test
    public void deleteUserTest() {
        given().delete("https://reqres.in/api/users/2").then().statusCode(204).log().all();

    }
}
