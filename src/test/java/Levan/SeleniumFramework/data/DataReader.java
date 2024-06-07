package Levan.SeleniumFramework.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

	public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
		String dir = System.getProperty("user.dir");
		System.out.println(dir);
//		get json as string
		String JsonContent =  FileUtils.readFileToString(new File(dir + "\\src\\test\\java\\Levan\\SeleniumFramework\\data\\PurchaseOrder.json"), StandardCharsets.UTF_8);
//		convert to hashmap - Jackson databind in dependencies
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(JsonContent, new TypeReference<List<HashMap<String,String>>>(){});
//		data = {map,map}
		return data;
		
	}

}
