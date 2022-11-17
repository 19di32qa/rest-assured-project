package regresIN;

import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Login {

    @Test
    public void loginSuccessTest() {
        JSONObject params = new JSONObject();
        params.put("email", "eve.holt@reqres.in");
        params.put("password", "cityslicka");
        given().header("Content-Type","application/json").when().body(params.toString())
                .post("https://reqres.in/api/login").then().assertThat().statusCode(200).log().body();
    }
    @Test
    public void loginUnSuccessTest() {
        JSONObject params = new JSONObject();
        params.put("email", "peter@klaven");
       // params.put("password", "cityslicka");
        given().header("Content-Type","application/json").when().body(params.toString())
                .post("https://reqres.in/api/login").then().assertThat().statusCode(400).body("error", equalTo("Missing password")).log().body();
    }
}
