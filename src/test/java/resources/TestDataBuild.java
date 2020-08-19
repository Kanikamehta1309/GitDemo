package resources;

import java.util.ArrayList;
import java.util.List;

import pojoSerialization.AddPlace;
import pojoSerialization.DeletePlace;
//import pojoSerialization.DeletePlace;


public class TestDataBuild {

	public AddPlace AddPlacePayload(String name, String language, String address){
		
		pojoSerialization.AddPlace ap = new pojoSerialization.AddPlace();
		ap.setAccuracy("50");
		ap.setName(name);
		ap.setPhone_number("(+91) 983 893 3937");
		ap.setLanguage(language);
		ap.setWebsite("http://google.com");
		List<String> listTypes = new ArrayList<String>();
		listTypes.add(address);
		listTypes.add("shop");
		ap.setTypes(listTypes);

		pojoSerialization.AddLocation l = new pojoSerialization.AddLocation();
		l.setLat(-38.383494);
		l.setLng(33.427362);

		ap.setLocation(l);
		return ap;

	}

	public DeletePlace DeletePlacePayload(String place_id){
		pojoSerialization.DeletePlace dp = new pojoSerialization.DeletePlace();
		dp.setPlace_id(place_id);
		return dp;
		
		//return "{\r\n\"place_id\": \""+ placeId + "\"\r\n}";
	}
}
