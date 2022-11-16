package MyApi;
import io.restassured.http.ContentType;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static  io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static  org.hamcrest.Matchers.*;
public class DeleteData extends BaseData {
    @Test(dataProvider = "deletedUsers")
    public void deleteDataTest(Integer num) {
        given().header("Content-Type","application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
                .delete("/users/"+num).then().log().all();

    }

    @DataProvider(name = "deletedUsers")
    public Object[][] deleteData() {
        return new Object[][] {{3},{4}};
    }
}
