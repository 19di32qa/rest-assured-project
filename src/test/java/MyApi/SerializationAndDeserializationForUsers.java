package MyApi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.lang.reflect.Field;

import static org.hamcrest.Matchers.equalTo;

public class SerializationAndDeserializationForUsers {

    @Test(dataProvider = "usersInfo")
    public void test1(int id, String name, String lastName) {
        User user = new User(id, name, lastName);
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        RestAssured.given().header("Content-Type","application/json").body(gson.toJson(user)).post("http://localhost:3000/users")
                .then().statusCode(201).assertThat().body("id",equalTo(id)).log().all();

    }
    @Test
    public void test2() throws IOException, IllegalAccessException {
        File file = new File("C:\\Users\\Dima\\Desktop\\RestAssuredProject\\src\\test\\java\\TestData\\getUsers.xlsx");
        FileOutputStream fos = new FileOutputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Sheet1");
        createHeader(sheet);
        createTable(sheet,4);
        workbook.write(fos);
        fos.close();

    }
    public void createTable(XSSFSheet sheet,int num) throws IllegalAccessException {
        for(int i = 1;i<=num;i++) {
            GsonBuilder builder =new GsonBuilder();
            Gson gson = builder.create();
            RequestSpecification requestSpecification = RestAssured.given();
            String body = requestSpecification.get("http://localhost:3000/users/"+i).getBody().asString();
            User user = gson.fromJson(body,User.class);
            Field[] fields = user.getClass().getFields();
            Row row = sheet.createRow(i);
            for(int j = 0;j<fields.length;j++) {
                Object value = fields[j].get(user);
                Cell cell = row.createCell(j);
                if(value instanceof String) {
                    cell.setCellValue((String) value);
                }
                if(value instanceof Integer) {
                    cell.setCellValue((Integer) value);
                }
            }
        }
    }
    public void createHeader(XSSFSheet sheet) {
        Row row = sheet.createRow(0);
        String headers[] = {"id","name","lastName"};
        for (int i =0;i<headers.length;i++) {
            row.createCell(i).setCellValue(headers[i]);
        }
    }

    @DataProvider(name = "usersInfo")
    public Object[][] getUsersInfo() throws IOException {

        FileInputStream fis = new FileInputStream("C:\\Users\\Dima\\Desktop\\RestAssuredProject\\src\\test\\java\\TestData\\users.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet("Sheet");
        Object users[][] = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
        int l =0;
        for(int i =1;i<=sheet.getLastRowNum();i++) {
            Row row = sheet.getRow(i);
            for (int j =0;j<row.getLastCellNum();j++) {
                Cell cell = row.getCell(j);
                //System.out.println(cell.getCellType() == CellType.NUMERIC);
                if(cell.getCellType() == CellType.NUMERIC) {
                    int value = (int) Math.round(cell.getNumericCellValue());
                    users[l][j] = value;
                }
                else {
                    users[l][j] = cell.getStringCellValue();
                }
            }
            l++;
        }
        return users;
    }
}
