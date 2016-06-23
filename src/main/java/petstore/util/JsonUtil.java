package petstore.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	
	public static String toJson(Object obj) throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		
		return mapper.writeValueAsString(obj);
		
	}
}
