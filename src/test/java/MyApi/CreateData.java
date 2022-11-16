package MyApi;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static  io.restassured.RestAssured.*;
import static  org.hamcrest.Matchers.*;

public class CreateData extends BaseData {

    @Test(dataProvider = "users")
    public void updateUserTest(String name,Integer id,String lastName) {
        JSONObject obj = new JSONObject();
        obj.put("name",name);
        obj.put("id",id);
        obj.put("lastName",lastName);
        given().header("Content-Type","application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
                .when().body(obj.toString()).post("/users").then().statusCode(201).body("name",equalTo(name)).log().all();

    }
    @DataProvider(name = "users")
    public Object[][] getUsers() {
        return new Object[][] {{"Vas",3,"Montenegro"},{"Tom",4,"Angelo"}};
    }
}
