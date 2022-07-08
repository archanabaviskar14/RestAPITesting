package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	
	public static RequestSpecification req;
	ResponseSpecification res;
	public RequestSpecification requestSpecification() throws IOException
	{								
		//in java we can create file at runtime using fileOutputStream
		//its a good practice to log everything in separate file
		//if req is not null then everytime for DDT it will be replaced by new result
		if(req==null)
		{	
		PrintStream log=new PrintStream(new FileOutputStream("logging.txt")); //printstream talks abt where should we log everything
		req=new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
					.addQueryParam("key", "qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(log))	//filter will be applied on obj req so that it will log everything
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.build();
		
		return req;
		}
		return req;

	}
	public ResponseSpecification responseSpecification()
	{
		res=new ResponseSpecBuilder().expectStatusCode(200)
				 .build();
		
		return res;

	}
	
	//properties class will extract all values from any file with .properties extension
	//and fileInputStrream class will extract the path of that .properties file
	//then we will integrate them with load() method
	//and finally will extract property "baseUrl"
	public String getGlobalValue(String key) throws IOException
	{
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream("C:\\Users\\RavindraBaviskar\\eclipse-workspace\\api\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(key);
	}
	
	public String getJsonPath(Response response,String key)
	{
		String resp=response.asString();
		JsonPath js=new JsonPath(resp);
		return js.get(key).toString();
		
	}

}
