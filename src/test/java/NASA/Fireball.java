package NASA;
import org.testng.annotations.Test;

import static  io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static  org.hamcrest.Matchers.*;
public class Fireball {
    @Test
    public void testFireball() {
        given().get("https://ssd-api.jpl.nasa.gov/fireball.api?limit=20").then().log().body();
    }
}
