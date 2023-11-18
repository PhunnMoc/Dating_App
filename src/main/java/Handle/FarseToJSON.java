package Handle;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.List;

import Models.Message;
import Models.Profile;

public class FarseToJSON {
	public static String listMessageToJSON(List<Message> listMessage) throws Exception {
        // Chuyển danh sách thành chuỗi JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(listMessage);       
        System.out.println(json);
        return json;
    }
	public static String listProfileToJSON(List<Profile> listProfile) throws Exception {
        // Chuyển danh sách thành chuỗi JSON
		
        ObjectMapper objectMapper = new ObjectMapper();
		/*
		 * objectMapper.registerModule(new JavaTimeModule());
		 * objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		 */
        String json = objectMapper.writeValueAsString(listProfile);       
        System.out.println(json);
        return json;
    }
}