# FlattenJSON
PROBLEM : Write a program that takes a JSON object as input and outputs a flattened version of the JSON object, with keys as the path to every terminal value in the JSON structure. Output should be valid JSON.

=======================================================================================

SOLUTION:
It takes a JSON(string) as input, parse into JSONObject using json-simple-1.1.1.jar and outputs a flattened version of the JSON object.
Flattened version of the JSON is stored in a map as key-value pair.

=======================================================================================

 Adding json-simple-1.1.1.jar to CLASSPATH:
 1. Download json-simple-1.1.1.jar from 
 https://storage.googleapis.com/google-code-archive-downloads/v2/code.google.com/json-simple/json-simple-1.1.1.jar
 
 2. Set the JSON_JAVA environment variable to point to the directory where JSON.simple jar is stored.
 
    e.g. - export JSON_JAVA = /<dir-path-if-json-simple-1.1.1.jar>/
    
 3. Set the CLASSPATH environment variable to point to the JSON.simple jar location
 
    e.g. - export CLASSPATH = $CLASSPATH:$JSON_JAVA/json-simple-1.1.1.jar
    
 =======================================================================================
 
 Building FlattenJson.java :
 
 1. Make sure json-simple-1.1.1.jar is added to CLASSPATH(steps above).
 
 2. Build java solution as follows:
    javac FlattenJson.java
    
 =======================================================================================
  
 Ways to run FlattenJson:
 1. Reading input JSON from pipe:
 
    e.g. - "cat in.json | java FlattenJson"
    
 2. Use static JSON string specified in FlattenJson class.
 
    e.g. - java FlattenJson
           <Press 'Enter' key after message> 
           
 3. Reading the JSON file passed as command line argument.
 
    e.g. - java FlattenJson in.json
    
 ========================================================================================
