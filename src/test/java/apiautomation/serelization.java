package apiautomation;

import io.restassured.RestAssured;
import pojo.location;
import pojo.serelizationClass;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class serelization {
	
	public static void main(String args[])
	{
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		serelizationClass sc=new serelizationClass();
		sc.setAccuracy(50);
		sc.setAddress("village galu jassal");
		sc.setLanguage("Hindi");
		location lc = new location();
		lc.setLat(10.1);
		lc.setLng(12.5);
		sc.setLocation(lc);
		
		sc.setName("Sharma house");
		sc.setPhone_number("9459193601");
		sc.setWebsite("https://www.hackysecurity.com");
         List<String>st=new ArrayList();
         st.add("shoe park");
         st.add("shop");
         sc.setTypes(st);
         
		String response=given().queryParam("key","qaclick123")
		.body(sc)
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println(response);
		
		
		
	}

}
