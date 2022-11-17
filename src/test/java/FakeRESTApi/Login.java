package FakeRESTApi;
import org.testng.annotations.Test;

import static  io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static  org.hamcrest.Matchers.*;
public class Login {

    @Test
    public void loginTest() {
        String userName = "test";
        String password = "12345678";
        given().get("https://petstore.swagger.io/v2/user/login?"+"username="+userName +"&"+"password="+password)
                .then().statusCode(200).log().all();
    }
    @Test public void Logout() {
        given().get("https://petstore.swagger.io/v2/user/logout").then().statusCode(200).log().all();
    }

}
