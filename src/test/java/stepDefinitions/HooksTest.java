package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class HooksTest {
	
	@Before("@DeletePlace")
	public void getPlaceIdForDeleteAPI() throws IOException
	{
		//execute this code only when place_id is null
		//write a code that will give u place_id
		//now all the methods r present in placevalidationstep class so create obj of it in order to access methods of it
		
		placevalidationstep ps=new placevalidationstep();
		if(placevalidationstep.place_id==null)   //here place_id we declared as static so use classname.variable
		{
		ps.add_place_payload_with("archana","london","english");
		ps.user_calls_with_http_request("addPlaceAPI","POST");
		ps.verify_place_id_created_maps_to_using("archana","getPlaceAPI");
		}
	}

}
