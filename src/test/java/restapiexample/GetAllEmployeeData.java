package restapiexample;
import org.testng.annotations.Test;

import static  io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static  org.hamcrest.Matchers.*;
public class GetAllEmployeeData {
    @Test
    public void getAllData() {
        given().get("https://dummy.restapiexample.com/api/v1/employees").then().assertThat()
                .body(matchesJsonSchemaInClasspath("employeeData.json")).log().body();

    }
    @Test
    public void getSingleEmployee() {
        given().get("https://dummy.restapiexample.com/api/v1/employee/1").then().assertThat()
                .body("data.id",equalTo(1)).body("status",equalTo("success"))
                .log().body();
    }
}
