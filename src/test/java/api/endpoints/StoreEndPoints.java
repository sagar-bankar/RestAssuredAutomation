package api.endpoints;
import static io.restassured.RestAssured.*;

import api.payload.Store;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class StoreEndPoints {

	
	public static Response createUser(Store storepay) {
		
		Response res=given()
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.body(storepay)
		
		.when()
		   .post(Routes.post_url_store);
		return res;
		
	}
	
public static Response getUser(Store storepay) {
		
		Response res=given()
		.accept(ContentType.JSON)
		
		
		
		.when()
		   .put(Routes.update_url_store);
		
		return res;
		
	}

public static Response updateUser(Store storepay) {
	
	Response res=given()
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.body(storepay)
	
	.when()
	   	.post(Routes.update_url_store);
	return res;
	
}
	
}
