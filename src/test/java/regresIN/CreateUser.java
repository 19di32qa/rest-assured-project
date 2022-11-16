package regresIN;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import static  io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static  org.hamcrest.Matchers.*;

public class CreateUser {

    @Test
    public void CreateUserV1Test() {
        RequestSpecification requestSpecification = RestAssured.given();
        JSONObject obj = new JSONObject();
        obj.put( "name", "morpheus");
        obj.put("job", "leader");
        requestSpecification.header("Content-Type","application/json");
        requestSpecification.body(obj.toString());
        Response response =  requestSpecification.request(Method.POST,"https://reqres.in/api/users");
        System.out.println(response.getBody().asString());
        System.out.println((String) response.getBody().jsonPath().get("name"));
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());
    }
    @Test public void CreateUserV2Test() {
        JSONObject obj = new JSONObject();
        String items[] = {"morpheus","leader"};
        obj.put( "name", "morpheus");
        obj.put("job", "leader");
        given().body(obj.toString()).header("Content-Type","application/json")
                .post("https://reqres.in/api/users").then().statusCode(201).body("name", equalTo("morpheus")).log().all();
    }

}
