package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import resources.payloads.ResponsePayload;
import resources.utilities.JavaUtilHelper;

public class CoursePriceTests {

	JsonPath js;

	@Test
	public void mockResponseTesting() {
		
		js = JavaUtilHelper.stringToJson(ResponsePayload.responseCoursePrice());
		int amountOfaCourse = 0;
		int grossPrice = 0;

		int count = js.getInt("courses.size()");
		System.out.println("\n1) Number of objects/courses in courses array : " + count);

		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println("\n2) Purchase amount : " + purchaseAmount);

		String firstCourse = js.getString("courses[0].title");
		System.out.println("\n3) Title of first course : " + firstCourse);

		System.out.println("\n4) Print all course titles and their respective prices : ");
		for (int i = 0; i < count; i++) {
			String courseTitle = js.getString("courses[" + i + "].title");
			int coursePrice = js.getInt("courses[" + i + "].price");
			int courseCopies = js.getInt("courses[" + i + "].copies");

			System.out.println("\n\tTitle of course " + i + " : " + courseTitle);
			System.out.println("\tPrices of " + courseTitle + " course " + i + " : " + coursePrice);
			System.out.println("\tCopies of " + courseTitle + " course " + i + " : " + courseCopies);

			amountOfaCourse = coursePrice * courseCopies;
			System.out.println("\tTotal cost of " + courseTitle + " course : " + amountOfaCourse);

			grossPrice += amountOfaCourse;
		}

		int i = 0;
		while (i < count) {
			String courseTitle = js.getString("courses[" + i + "].title");
			if (courseTitle.equalsIgnoreCase("Cypress")) {
				int courseCopies = js.getInt("courses[" + i + "].copies");
				System.out.println("\n5) Number of copies sold by " + courseTitle + " course : " + courseCopies);
				break;
			}
			i++;
		}

		System.out.println("\n6) Sum of all course prices : " + grossPrice);
		System.out.println("   Purchase amount : " + purchaseAmount);

		Assert.assertEquals(grossPrice, grossPrice);
		System.out.println("   Sum of all course prices matched successfully with purchase amount !!!\n\n");

	}

}
