package apiautomation;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.bodyPayload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;


public class DynamicJson {
	
	@Test(dataProvider="Booksdata")
	public void addBook(String isbn,String aisle) {
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
	    
		String responsePost=given().header("Content-Type","application/json").body(bodyPayload.bookPayload(isbn,aisle))
		.when().post("Library/Addbook.php")
		.then().log().all().statusCode(200).extract().response().asString();
		
		JsonPath js=new JsonPath(responsePost);
		String bookID=js.getString("ID");
		System.out.println(bookID);
		
	}
	
	@DataProvider(name="Booksdata")
	public Object[][] getData()
	{
		return new Object[][] {{"ojfeyyewty","9356663"},{"cweeehhtee","462543"},{"okmhhfet","536363 "}};
	}

}
