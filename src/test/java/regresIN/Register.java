package regresIN;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static  io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static  org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
public class Register {

    @Test
    public void registerTest() {
        JSONObject params = new JSONObject();
        params.put("email", "eve.holt@reqres.in");
        params.put("password", "pistol");

        given().header("Content-Type","application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
                .when().body(params.toString()).post("https://reqres.in/api/register").then().assertThat()
                .statusCode(200).body("id",equalTo(4)).log().body();
    }
    @Test
    public void registerUnsuccessful() {
        JSONObject params = new JSONObject();
        params.put("email","sydney@fife");
        given().header("Content-Type","application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
                .when().body(params.toString()).post("https://reqres.in/api/register").then().assertThat()
                .statusCode(400).body("error",equalTo("Missing password")).log().body();
    }
}
