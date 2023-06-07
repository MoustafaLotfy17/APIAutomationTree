package AutomationTask.API_Task;

import static org.testng.Assert.assertEquals;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import io.qameta.allure.Feature;
import io.restassured.response.Response;
import junit.framework.Assert;

import resources.CommonTestUtils;
import resources.Constants;

import java.util.HashMap;
import java.util.List;

@Feature("Get & Post reqres portal API")
public class GetProductAPITest extends CommonTestUtils {

	private static final String requestBody = "{\"name\":\"John\", \"age\":30}";

	@Test(description = "Get Product Request with Valid Product ID.")
	public void getProductRequestIfIdIsValid() {
		Response response = sendGetRequest(Constants.getBaseURL() + "?page=2");
		// Get & Print the response body
		String responseBody = response.getBody().asString();
		System.out.println("Response Body:\n" + responseBody);

		// assertions
		assertEquals(response.getStatusCode(), 200);
		JsonPath path = response.jsonPath();
		List<HashMap<String, Object>> data = path.getList("data");
		for (HashMap<String, Object> singleObject : data) {
			if (singleObject.get("id").equals(10)) {
//				System.out.println(singleObject.get("first_name"));
				String firstName = (String) singleObject.get("first_name");
				Assert.assertEquals(firstName, "Byron");

			}
		}
	}

	@Test(description = "Get Product Request with Invalid Product ID.")
	public void getProductRequestIfIdIsInvalid() {

		Response response = sendPostRequest(Constants.getBaseURL(),requestBody );

		// Get & Print the response body
		String responseBody = response.getBody().asString();
		System.out.println("Response Body:\n" + responseBody);

		// assertions
		assertEquals(response.getStatusCode(), 201);
		int generatedId = response.jsonPath().getInt("id");
		Assert.assertTrue("ID Is Not Generated", generatedId != 0);
	}
}
