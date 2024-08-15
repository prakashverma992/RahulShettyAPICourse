package resources.utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

import io.restassured.path.json.JsonPath;

public class JavaUtilHelper {

	public static JsonPath stringToJson(String response) {
		JsonPath js = new JsonPath(response);
//		js.prettyPrint();

		return js;
	}

	public static String getRandomString(int length) {
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			int index = random.nextInt(alphabet.length());
			char randomChar = alphabet.charAt(index);
			sb.append(randomChar);

		}
		String randomString = sb.toString();

		return randomString;
	}

	public static String generateRandomString(int count) {

		return RandomStringUtils.randomAlphanumeric(count);
	}

	public static Integer getRandomInteger() {
		Random random = new Random();
		int randomInteger = random.nextInt(1000);

		return randomInteger;
	}

	public static String getStringPayloadFromJSON(String path) throws IOException {

		return new String(Files.readAllBytes(Paths.get(path)));
	}

	public static List<String> arrayToStringConverion(ArrayList<String> arrayList) {
		List<String> output = new ArrayList<String>(arrayList.size());
		for (Object obj : arrayList) {
			output.add(Objects.toString(obj, null));
		}

		return output;

	}

}
