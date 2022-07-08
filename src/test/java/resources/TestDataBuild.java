package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {

	public AddPlace addPlacePayload(String name, String address, String language)
	{
		AddPlace ap=new AddPlace();
		ap.setAccuracy(50);
		ap.setName(name);
		ap.setPhone_number("(+91) 983 893 3937");
		ap.setAddress(address);
		ap.setWebsite("http://google.com");
		ap.setLanguage(language);
		
		List<String> mylist=new ArrayList<String>();
		mylist.add("shoe park");
		mylist.add("shop");
		ap.setTypes(mylist);
		
		Location l =new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		ap.setLocation(l);
		
		return ap;            //here we r returning object of AddPlace so add Addplace as a return type
		
	}
	
	public String DeletePlacePayload(String place_id)
	{
		return "{\r\n"
				+ "    \"place_id\":\""+place_id+"\"\r\n"
				+ "}";
		
	}
}
