package MyApi;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static  io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static  org.hamcrest.Matchers.*;

public class UpdateData extends BaseData{

    @Test
    public void updateData() {
        JSONObject obj = new JSONObject();
        obj.put("name","Tom");
        obj.put("id",3);
        obj.put("lastName","Angelo");
        given().header("Content-Type","application/json").contentType(ContentType.JSON).accept(ContentType.JSON).when().body(obj.toString()).put("/users/3").then().statusLine("HTTP/1.1 200 OK").log().all();
    }
}
