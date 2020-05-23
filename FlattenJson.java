import java.io.*;
import java.util.LinkedHashMap;
import java.util.Scanner;

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
		String inputJson = "";
		FileReader fileReader = null;
		boolean isFileArg = false;
		
		// Read from pipe 
		// e.g. "cat in.json | java FlattenJson"
		if (args.length == 0){
			Scanner sc = new Scanner(System.in);
			System.out.println("Press enter for not passing file as argument or pipe input and reading static input JSON");
	        	inputJson = sc.nextLine();
	        	// Read input string json rather than taking it as pipe input.
			if(inputJson.isEmpty())
				inputJson = input;
			else {
				// Read input json from pipe input
				while (sc.hasNextLine()) {
					inputJson += sc.nextLine();
				}
			}	
		} else {
			// Read from json file passed as command line argument
			// e.g. "java FlattenJson in.json "
			String fileName = args[0];
			try {
				System.out.println("Reading the JSON file passed as arg : " + fileName);
				fileReader = new FileReader(fileName);
				isFileArg = true;
			} catch (FileNotFoundException ex) {
				System.out.println("Unable to open file '" + fileName + "'");
			}
		}
		
		// Parsing JSON from string/file to JSONObject using json-simple-1.1.1.jar
		JSONParser parser = new JSONParser();
		Object jsonObj = null;
		try {
			if(isFileArg)
				jsonObj = parser.parse(fileReader);
			else
				jsonObj = parser.parse(inputJson);
			
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

