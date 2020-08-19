package stepDefinations;

import java.io.IOException;
import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeDelete() throws IOException{
		AddPlace ad = new AddPlace();
		if(AddPlace.place_id==null){
		ad.add_Place_Request_Payload_with("QA", "Selenium", "USA");
		ad.user_calls_Http_request("AddPlaceAPI", "POST");
		ad.verify_the_of_in_response_code_using("place_id", "QA", "GetPlaceAPI", "GET");
		}
	}
	
	public void gitTest(){
		System.out.println("Git Testing in dev mode");
		System.out.println("added in api framework");
	}
}
