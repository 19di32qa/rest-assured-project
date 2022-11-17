package restapiexample;
import org.testng.annotations.Test;

import static  io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static  org.hamcrest.Matchers.*;
public class DeleteData {

    @Test
    public void deleteUser() {
        given().delete("https://dummy.restapiexample.com/api/v1/delete/2").then().log().all();
    }
}
