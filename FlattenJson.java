import java.util.LinkedHashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class FlattenJson {
	static String input = "{\n" + "    \"a\": 1,\n" + "    \"b\": true,\n" + "    \"c\": {\n" + "        \"d\": 3,\n"
			+ "        \"e\": \"test\",\n" + "	     \"f\": {\n" + "        	\"g\": 3,\n"
			+ "        	\"h\": false,\n" + "        }\n" + "    }\n" + "}";
   
	/**
	* Method to read json as JsonObject and flatten it into Map.
	*
	* @param jsonObject - JsonObject after parsing JSON string to an object using json-simple-1.1.1.jar
	* @param jsonMap - Out parameter to store falttened JSON as key-value pair
	* @param parentKey - Parent JsonObject-key is passed, in case of nested JsonObject 
	*/
	public static void populateJsonMap(JSONObject jsonObject, LinkedHashMap<String, String> jsonMap, String parentKey) {
		for (Object key : jsonObject.keySet()) {
			String strKey = (String) key;
			String orgkey = strKey;
			if (parentKey != null) {
				strKey = parentKey + "." + strKey;
			}
			Object keyvalue = jsonObject.get(orgkey);
			if (keyvalue instanceof JSONObject) {
				populateJsonMap((JSONObject) keyvalue, jsonMap, strKey);
			} else {
				jsonMap.put(strKey, String.valueOf(keyvalue));
			}
		}
	}

	public static void main(String[] args) {
		LinkedHashMap<String, String> jsonMap = new LinkedHashMap<String, String>();
		// Parsing JSON from string to JSONObject using json-simple-1.1.1.jar
		JSONParser parser = new JSONParser();
		Object jsonObj = null;
		try {
			jsonObj = parser.parse(input);
			JSONObject jsonObject = (JSONObject) jsonObj;
			populateJsonMap(jsonObject, jsonMap, null);
			for (String key : jsonMap.keySet()) {
				System.out.println(key + " : " + jsonMap.get(key));
			}
		} catch (Exception ex) {
			System.out.println("Exception: " + ex.getMessage());
			System.out.println(ex);
		}
	}
}

