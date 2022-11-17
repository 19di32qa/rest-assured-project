package FakeRESTApi;
import org.testng.annotations.Test;

import static  io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static  org.hamcrest.Matchers.*;
public class getActivities {

    @Test
    public void getActivitiesTest() {
        given().get("https://fakerestapi.azurewebsites.net/api/v1/Activities").then().assertThat()
                .body(matchesJsonSchemaInClasspath("Activities.json")).log().body();
    }
    @Test
    public void getSingleActivitiesTest() {
        given().get("https://fakerestapi.azurewebsites.net/api/v1/Activities/1").then().assertThat()
                .body(matchesJsonSchemaInClasspath("Activities.json")).log().body();

    }

}
