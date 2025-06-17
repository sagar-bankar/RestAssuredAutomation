package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTests {

	//@Test(priority = 1, dataProvider = "datafor_UserModule", dataProviderClass = DataProviders.class)
	void testPostUser(String userID, String username, String Fname, String Lname, String email, String pwd, String ph) {

		User userPayload = new User();
		Faker faker = new Faker();

		userPayload.setId((int) Double.parseDouble(userID));
		 //userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(username);
		userPayload.setFirstName(Fname);
		userPayload.setLastName(Lname);
		userPayload.setEmail(email);
		userPayload.setPassword(pwd);
		userPayload.setPhone(ph);

		Response response = UserEndPoints.createUser(userPayload);
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	//@Test(priority = 2, dataProvider = "datafor_UserModule", dataProviderClass = DataProviders.class)
	public void testDeleteUserByName(String userID, String username, String Fname, String Lname, String email,
			String pwd, String ph) {
		Response response = UserEndPoints.deleteUser(username);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
