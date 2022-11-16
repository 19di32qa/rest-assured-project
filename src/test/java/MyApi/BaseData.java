package MyApi;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import static  io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static  org.hamcrest.Matchers.*;

public class BaseData {
    @BeforeClass
    public void setUp() {
        baseURI = "http://localhost:3000/";
        //given().header("Content-Type","application/json").contentType(ContentType.JSON).accept(ContentType.JSON);

    }

}
