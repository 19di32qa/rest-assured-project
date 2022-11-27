package MyApi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class SerializationAndDeserialization {

    @Test(dataProvider = "posts")
    public void test1(String id,String title, String author) {
        PostClass post1 = new PostClass(Math.round(Float.parseFloat(id)),title,author);
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        gson.toJson(post1);
        System.out.println(gson.toJson(post1));
        given().header("Content-Type","application/json").contentType(ContentType.JSON).body(gson.toJson(post1))
                .post("http://localhost:3000/posts").then().assertThat().statusCode(201).body("id",equalTo(post1.id)).log().all();
    }
    @Test
    public void test2() throws IllegalAccessException, IOException, InvalidFormatException {
        RequestSpecification requestSpecification = given();
        Response response = requestSpecification.get("http://localhost:3000/posts/1");
        String body = response.getBody().asString();
        System.out.println(body);
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        PostClass post = gson.fromJson(body, PostClass.class);
        Field[] fields = PostClass.class.getFields();
        File file = new File("C:\\Users\\Dima\\Desktop\\RestAssuredProject\\src\\test\\java\\TestData\\getPosts.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("posts");
        int rowNum = 0;
        Row row = sheet.createRow(rowNum++);
        String[] fieldsForXSLX = {"id","title","author"};
        for(int i =0;i<3;i++) {
             Cell cell = row.createCell(i);
             cell.setCellValue(fieldsForXSLX[i]);
        }

        Row row1 = sheet.createRow(rowNum++);
        for (int i =0;i<fields.length;i++) {
            Object value = fields[i].get(post);
            System.out.println(value);
            Cell cell = row1.createCell(i);
            if(value instanceof String) {
                cell.setCellValue((String) value);
            }
            if (value instanceof Integer) {
                cell.setCellValue((Integer) value);
            }
            //System.out.println(fields[i]);
        }
        FileOutputStream fos = new FileOutputStream(file);
        workbook.write(fos);
        fos.close();


    }

    @DataProvider(name = "posts")
    public Object[][] getPosts() throws IOException {
        FileInputStream fis = new FileInputStream("C:\\Users\\Dima\\Desktop\\RestAssuredProject\\src\\test\\java\\TestData\\posts.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet("posts");


        String posts[][] = new String[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
        int l =0;

        for(int i=1;i<=sheet.getLastRowNum();i++) {
            Row row = sheet.getRow(i);
            for(int j =0;j<row.getLastCellNum();j++) {
                String cell =String.valueOf(row.getCell(j));
                posts[l][j] = cell;
            }
            l++;
        }

        return posts;

    }

}
