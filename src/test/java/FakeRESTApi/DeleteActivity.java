package FakeRESTApi;
import org.testng.annotations.Test;

import static  io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static  org.hamcrest.Matchers.*;
public class DeleteActivity {

    @Test
    public void deleteActivityTest() {
        given().header("Content-Type","application/json").when()
                .delete("https://fakerestapi.azurewebsites.net/api/v1/Activities/0").then()
                .assertThat().statusCode(200).log().headers();
    }
}
