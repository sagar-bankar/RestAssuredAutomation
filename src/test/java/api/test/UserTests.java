package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {

	Faker faker;
	User userPayload;
	public Logger logger;
	@BeforeClass
	public void setUpData() {
		faker = new Faker();
		userPayload = new User();

		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		logger= LogManager.getLogger(this.getClass());
		
	}

	@Test(priority = 1)
	void testPostUser() {
		logger.info("*********Creating User*****************");
		Response response = UserEndPoints.createUser(userPayload);
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 2)
	public void testGetUserByName() throws InterruptedException {
		logger.info("*********User get***************");
		Thread.sleep(1000); // optional wait
		Response response = UserEndPoints.readUser(this.userPayload.getUsername());
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 3)
	public void testUpdateUserByName() throws InterruptedException {
		logger.info("*********Upadte User***************");
		
		// update first name and lastName
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		 Thread.sleep(1000); // optional wait
		Response responseUpdate = UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
		responseUpdate.then().log().body().statusCode(200);

		Assert.assertEquals(responseUpdate.getStatusCode(), 200);
		 Thread.sleep(1000); // optional wait
		// checking data after update

		Response responseAfterUpdate = UserEndPoints.readUser(this.userPayload.getUsername());
		responseAfterUpdate.then().log().all();
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);

	}

	@Test(priority = 4 )
	public void testDeleteUserByName() throws InterruptedException {
		logger.info("*********Deleting User***************");
		System.out.println("USERNAME TO DELETE: " + this.userPayload.getUsername());  // 🔍
		 Thread.sleep(1000); // optional wait
		Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());
		response.then().log().all(); // log API response
		 Thread.sleep(1000); // optional wait
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
