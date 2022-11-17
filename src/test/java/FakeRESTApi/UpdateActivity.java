package FakeRESTApi;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static  io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static  org.hamcrest.Matchers.*;
public class UpdateActivity {
    @Test
    public void updateActivityTest() {
        JSONObject params = new JSONObject();
        params.put("id",0);
        params.put("title","Sport");
        params.put("dueData","2022-11-17T14:14:33.016Z");
        params.put("completed",true);
        given().header("Content-Type","application/json").when()
                .body(params.toString()).put("https://fakerestapi.azurewebsites.net/api/v1/Activities/0").then()
                .assertThat().statusCode(200).body("title",equalTo("Sport")).log().body();
    }
}

