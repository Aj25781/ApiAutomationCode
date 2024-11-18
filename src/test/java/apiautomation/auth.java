package apiautomation;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import pojo.getAllCourseDetail;
import pojo.webAutomation;

import static io.restassured.RestAssured.*;

import java.util.List;

public class auth {
	
	public static void main(String args[])
	{
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		String response=given().formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W").formParams("grant_type", "client_credentials")
		.when().post("oauthapi/oauth2/resourceOwner/token")
		.then().log().all().extract().response().asString();
		
		JsonPath js =new JsonPath(response);
		String accessToken=js.getString("access_token");
		System.out.println(accessToken);
		
		//getcoursedetails using auth token 
		getAllCourseDetail gs=given().queryParams("access_token",accessToken)
		.when().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(getAllCourseDetail.class);
		System.out.println(gs.getLinkedIn());
		
		List<webAutomation> webElements=gs.getCourses().getWebAutomation();
		for(int i=0;i<webElements.size();i++)
		{
			
			System.out.println(webElements.get(i).getCourseTitle());
			
		}
		
		
		
	}

}
