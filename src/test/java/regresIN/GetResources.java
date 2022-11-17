package regresIN;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class GetResources {
    @Test
    public void getListOfResources() {
        given().get("https://reqres.in/api/unknown").then().assertThat()
                .body(matchesJsonSchemaInClasspath("resources.json")).statusCode(200).log().all();
    }
    @Test
    public void getSingleResources() {
        given().get("https://reqres.in/api/unknown/2").then().assertThat().statusCode(200)
                .body("data.name", equalTo("fuchsia rose")).log().all();
    }
    @Test
    public void resourcesNotFound() {
        given().get("https://reqres.in/api/unknown/23").then().assertThat().statusCode(404).statusLine("HTTP/1.1 404 Not Found").log().all();
    }
}
