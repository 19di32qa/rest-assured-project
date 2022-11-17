package restapiexample;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static  io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static  org.hamcrest.Matchers.*;
public class CreateEmployee {

    @Test
    public void createUserTest() {
        JSONObject params = new JSONObject();
        params.put("name","John");
        params.put("salary",135);
        params.put("age",25);

        given().header("Content-Type","application/json").when().body(params.toString())
                .post("https://dummy.restapiexample.com/api/v1/create").then().assertThat()
                .statusCode(200).body("status",equalTo("success")).log().all();
    }
}
