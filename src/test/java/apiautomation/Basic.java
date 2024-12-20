package apiautomation;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.bodyPayload;

public class Basic {
	
	public static void main(String args[])
	{
		//Validate if add place api is working fine . 
		//given- all input details 
		//when- submit the api - resouce and http method will go in when method 
		//then- validate the response . 
		
		//Post api *** 
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		
		String response=given().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body(bodyPayload.payload()).when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope",equalTo("APP"))
		.header("server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
		
		System.out.println(response);
		
		JsonPath js =new JsonPath(response);
		String placeId=js.getString("place_id");
		
		System.out.println(placeId); 
		
		//Update place****
		String newAddress="winter walk, USA";
		given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json").body("{\r\n"
				+ "\"place_id\":\""+placeId+"\",\r\n"
				+ "\"address\":\""+newAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}").when().put("maps/api/place/update/json").then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		//Get Place api ***
		
		String getPlaceAddress=given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId).when().get("maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
		
		System.out.println(getPlaceAddress);
		
		JsonPath js1=new JsonPath(getPlaceAddress);
		String actualAddress=js1.getString("address");
		System.out.println(actualAddress);
		Assert.assertEquals(actualAddress,actualAddress);
		
		
		
	}
}
