package resources;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CommonTestUtils {

	public static Response sendGetRequest(String restURL) {
		return given().get(restURL);
	}

	public static Response sendPostRequest(String restURL, String requestBody) {
		return 	given()
				.contentType(ContentType.JSON)
				.body(requestBody)
				.post(restURL);
	}
}
