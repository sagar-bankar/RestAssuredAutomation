package api.endpoints;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payload.User;

//UserEndPoints.java---->created for perform CRUD operations 

public class UserEndPoints2 {
	
	//metod created getting urls from properties file
     static ResourceBundle getUrl(){
		ResourceBundle routes=ResourceBundle.getBundle("routes");
		return routes;
	}
	
	public static Response createUser(User payload)
	{
		String post_url=getUrl().getString("post_url");
		
		
		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				
		.when().post(post_url);
		
		return response;
	}
	
	
	public static Response readUser(String username)
	{
		String get_url=getUrl().getString("get_url");
		
		Response response=given()
				.pathParam("username", username)
				
		.when()
		        .get(get_url);
		
		return response;
	}
	
	public static Response updateUser(String username,User payload)
	{
		String update_url=getUrl().getString("update_url");
		
		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", username)
				.body(payload)
				
		.when()
				.put(update_url);
		
		return response;
	}
	
	
	public static Response deleteUser(String username)
	{
		String 	delete_url=getUrl().getString("delete_url");
		
		Response response=given()
				.pathParam("username", username)
				
		.when()
		        .delete(delete_url);
		
		return response;
	}
	

}
