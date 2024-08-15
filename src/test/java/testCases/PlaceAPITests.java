package testCases;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import resources.payloads.RequestPayload;
import resources.utilities.JavaUtilHelper;

public class PlaceAPITests {

	private String baseURI = "https://rahulshettyacademy.com/maps/api/place";
	private String placeId, URIPath, responseBody, address, updatedAddress;
	private JsonPath js;
	private Map<String, String> queryParams = new LinkedHashMap<String, String>();

	@Test(priority = 1)
	public void addPlaceAPI() throws IOException {
		RestAssured.baseURI = baseURI;
		URIPath = "/add/json";
		responseBody = given().log().all().queryParam("key", "qaclick123").contentType(ContentType.JSON)
//				.body(RequestPayload.addPlace())
				.body(JavaUtilHelper.getStringPayloadFromJSON("src/test/java/resources/payloads/addPlace.json"))
				.when()
				.post(URIPath).then().assertThat().statusCode(200).body("scope", equalTo("APP"))
				.body("status", equalTo("OK")).header("server", "Apache/2.4.52 (Ubuntu)").extract().response()
				.asString();

		System.out.println("\nResponse Body: \n\t" + responseBody);

		js = JavaUtilHelper.stringToJson(responseBody);
		placeId = js.getString("place_id");

	}

	@Test(priority = 2)
	public void updatePlaceAPI() {
		RestAssured.baseURI = baseURI;
		URIPath = "/update/json";
		System.out.println("\nPlaceId: \n" + placeId);
		queryParams.clear();
		queryParams.put("key", "qaclick123");
		queryParams.put("place_id", placeId);
		responseBody = given().log().all().queryParams(queryParams).contentType(ContentType.JSON)
				.body(RequestPayload.updatePlace(placeId)).when().put(URIPath).then().log().all().statusCode(200)
				.body("msg", equalTo("Address successfully updated")).extract().response().asString();

		System.out.println("\nResponse Body: \n\t" + responseBody);

		String getResponse = given().log().all().queryParams(queryParams).when().get("/get/json").then().assertThat()
				.statusCode(200).extract().response().asString();

		js = JavaUtilHelper.stringToJson(getResponse);
		updatedAddress = js.getString("address");
		System.out.println(address);
		System.out.println(updatedAddress);
		Assert.assertNotEquals(address, updatedAddress);

	}

//	@Test(priority = 3)
//	public void deletePlaceAPI() {
//
//	}

	@AfterMethod
	public void getPlaceAPI() {
		RestAssured.baseURI = baseURI;
		URIPath = "/get/json";
		System.out.println("\nPlaceId: \n" + placeId);
		queryParams.clear();
		queryParams.put("key", "qaclick123");
		queryParams.put("place_id", placeId);

		responseBody = given().log().all().queryParams(queryParams).when().get(URIPath).then().assertThat()
				.statusCode(200).extract().response().asString();

		System.out.println("\nResponse Body: \n\t" + responseBody);

		js = JavaUtilHelper.stringToJson(responseBody);
		address = js.getString("address");

	}

}
