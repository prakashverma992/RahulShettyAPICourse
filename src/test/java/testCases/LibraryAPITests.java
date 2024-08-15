package testCases;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import resources.payloads.RequestPayload;
import resources.utilities.JavaUtilHelper;

public class LibraryAPITests {

	JsonPath js;
	String baseURI = "http://216.10.245.166";
	String Id, URIPath, response;
	ArrayList<String> listOfId = new ArrayList<String>();

	@Test(dataProvider = "BooksData")
	public void addBook(String isbn, Integer aisle) {
		RestAssured.baseURI = baseURI;
		URIPath = "/Library/Addbook.php";
		response = given().log().all().contentType(ContentType.JSON).body(RequestPayload.addBook(isbn, aisle)).when()
				.post(URIPath).then().log().all().statusCode(200).extract().response().asString();

		js = JavaUtilHelper.stringToJson(response);
		Id = js.get("ID");
		System.out.println(js.get("ID"));
		listOfId.add(Id);
		System.out.println(listOfId.toString());

	}

//	@Test
//	public void getBookById() {
//
//	}
//
//	@Test
//	public void getBookByAuthourName() {
//
//	}

	@Test(dataProvider = "DeleteBook")
	public void deleteBook(String Id) {
		RestAssured.baseURI = baseURI;
		URIPath = "/Library/DeleteBook.php";
		response = given().contentType(ContentType.JSON).body(RequestPayload.deleteBook(Id)).when().post(URIPath).then()
				.statusCode(200).log().all().extract().response().asString();

		js = JavaUtilHelper.stringToJson(response);
		Assert.assertEquals(js.getString("msg"), "book is successfully deleted");
	}

	@DataProvider(name = "BooksData")
	public Object[][] getData() {
		// array = collection of elements
		// multidimensional array = collection of arrays
		return new Object[][] { { JavaUtilHelper.getRandomString(4), JavaUtilHelper.getRandomInteger() },
				{ JavaUtilHelper.getRandomString(5), JavaUtilHelper.getRandomInteger() },
				{ JavaUtilHelper.getRandomString(3), JavaUtilHelper.getRandomInteger() } };
	}

	@DataProvider(name = "DeleteBook")
	public Object[] deleteData() {
		return new Object[] { "imaw111", "ruvai643", "vse826" };
//		return new Object[] { listOfId.toArray() };
	}

}
