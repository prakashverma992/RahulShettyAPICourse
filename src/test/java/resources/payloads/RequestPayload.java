package resources.payloads;

public class RequestPayload {

	public static String addPlace() {
		return "{\r\n"
				+ "    \"location\": {\r\n"
				+ "        \"lat\": -38.383494,\r\n"
				+ "        \"lng\": 33.427362\r\n"
				+ "    },\r\n"
				+ "    \"accuracy\": 50,\r\n"
				+ "    \"name\": \"Frontline house\",\r\n"
				+ "    \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "    \"address\": \"29, side layout, cohen 09\",\r\n"
				+ "    \"types\": [\r\n"
				+ "        \"shoe park\",\r\n"
				+ "        \"shop\"\r\n"
				+ "    ],\r\n"
				+ "    \"website\": \"http://google.com\",\r\n"
				+ "    \"language\": \"French-IN\"\r\n"
				+ "}";
	}

	public static String updatePlace(String placeId) {
		return "{\r\n"
				+ "    \"place_id\": \"" + placeId + "\",\r\n"
				+ "    \"address\": \"70 Summer walk, USA\",\r\n"
				+ "    \"key\": \"qaclick123\"\r\n"
				+ "}";
	}

	public static String deletePlace(String placeId) {
		return "{\r\n"
				+ "    \"place_id\": \"" + placeId + "\"\r\n"
				+ "}";
	}
	
	public static String addBook(String isbn, Integer aisle) {
		return "{\r\n"
				+ "    \"name\": \"Learn Appium Automation with Java\",\r\n"
				+ "    \"isbn\": \"" + isbn + "\",\r\n"
				+ "    \"aisle\": \"" + aisle + "\",\r\n"
				+ "    \"author\": \"John foe\"\r\n"
				+ "}";
	}
	
	public static String deleteBook(String Id) {
		return "{\r\n"
				+ "    \"ID\": \"" + Id + "\"\r\n"
				+ "}";
	}

}
