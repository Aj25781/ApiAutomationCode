package apiautomation;

import files.bodyPayload;
import io.restassured.path.json.JsonPath;

public class complexJasonParse {
	
	public static void main(String args[])
	{
		JsonPath js = new JsonPath(bodyPayload.complexPayload());
		
		int count =js.getInt("courses.size()");	
		System.out.println(count);
		
		//print purchase amount
		int totalAmount=js.getInt("dashboard.purchaseAmount");
		System.out.println(totalAmount);
		
		//print title of the first course 
		String firstTitle=js.get("courses[0].title");
		System.out.println(firstTitle);
		
		//Print all course titles and thier respective prices 
		
		for(int i=0;i<count;i++)
		{
			System.out.println("**********");
			String courseTitles=js.get("courses["+i+"].title");		
			System.out.println(courseTitles);
			int prices=js.getInt("courses["+i+"].price");
			System.out.println(prices);
		}
		
		
		//Print no of copies sold by RPA course
		System.out.println("Print no of copies sold by RPA course");
		
		for(int i=0;i<count;i++)
		{
			
			String courseTitles=js.get("courses["+i+"].title");		
			if(courseTitles.equalsIgnoreCase("RPA"))
			{
				
				int copyCount=js.get("courses["+i+"].copies");
				System.out.println(copyCount);
				break;
			}
		}
		
		//Verify if sum of all course prices matcheds with Purchase amount
		int result = 0;
		for(int i=0;i<count;i++)
		{
			int courseTitles=js.get("courses["+i+"].copies");		
			int prices=js.getInt("courses["+i+"].price");
			 result = result+courseTitles*prices;
			
		}
		if(totalAmount==result)
		{
			System.out.println("matches");
		}
		
	}

	
	
}
