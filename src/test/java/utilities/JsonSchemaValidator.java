package utilities;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;


public class JsonSchemaValidator {
    @Test
    public void getUsersV2Test() {
        String[] arr = {"Michael", "Lindsay", "Tobias", "Byron", "George", "Rachel"};
        given().header("Content-Type", "application/json").get("https://reqres.in/api/users?page=2")
                .then().assertThat().body(matchesJsonSchemaInClasspath("schema.json")).statusCode(200).log().all();

    }
}
