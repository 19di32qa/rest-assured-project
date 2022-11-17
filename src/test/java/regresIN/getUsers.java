package regresIN;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import static  io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static  org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
public class getUsers {

//    @Test
//    public void getUsersV1Test() {
//        RequestSpecification requestSpecification = RestAssured.given();
//        Response response = requestSpecification.request(Method.GET, "https://reqres.in/api/users?page=2");
//        String responseBody = response.getBody().asString();
//        System.out.println(responseBody);
//        System.out.println(response.getStatusLine());
//        System.out.println(response.getStatusCode());
//    }
    @Test
    public void getUsersV2Test() {
        String[] arr = {"Michael", "Lindsay", "Tobias", "Byron", "George", "Rachel"};
        given().header("Content-Type", "application/json").get("https://reqres.in/api/users?page=2")
                .then()
                .body("data[0].id",equalTo(7))
                .body("data.first_name", hasItems(arr))
                .statusCode(200).log().all();

    }
    @Test
    public void getSingleUser() {
        given().get("https://reqres.in/api/users/2").then().assertThat().statusCode(200)
                .body(matchesJsonSchemaInClasspath("single_user_schema.json")).body("data.id",equalTo(2)).log().all();
    }
    @Test
    public void userNotFound() {
        given().get("https://reqres.in/api/users/23").then().assertThat().statusCode(404).log().all();
    }
}
