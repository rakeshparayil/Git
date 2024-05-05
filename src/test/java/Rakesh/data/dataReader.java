package Rakesh.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class dataReader {
	
	
	public List<HashMap<String, String>> getJsonDataToMap() throws IOException
	{
		//read JSON to String
		
	String jsonContent =FileUtils.readFileToString(new File(System.getProperty("user.dir")+"src\\test\\java\\Rakesh\\data\\PuchaseOrder.json"),
			StandardCharsets.UTF_8);
		
	
	//String to Hashmap- need new dependency to be installed in pom.xml calleD
	//Jackson Databind
	ObjectMapper maper = new ObjectMapper();//
	//created object of class ObjectMapper- In this class there is a method called
	//readValue which can read the String and convert to HashMap
	//object(mapper).method(readValue)
	//Method expects two arguments
	//First is string need to convert to Hashmap
	//Second argument how you want to convert it
	//We have array with two indexes(two sets of data) present in Json file
	//Need to create two Hashmaps
	//two hashmaps place in a list
	//After it create a list and put those hashmaps and give back
	//data here a list with two argumnets as hashmap
	List<HashMap<String,String>> data = maper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {
	});
	return data;
	
	}
}


