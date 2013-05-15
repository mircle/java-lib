import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.quickbundle.project.tools.RmObjectMapper;
import org.quickbundle.tools.helper.RmVoHelper;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;


public class TestJackson {
	public static void main(String[] args) {
//		List<Message> lvo = new ArrayList<Message>();
//		lvo.add(new Message("1","a","a1"));
//		lvo.add(new Message("2","b","a1bbb"));
//		lvo.add(new Message("3","c","a1ccc"));
//			String result = RmVoHelper.writeBackListToRowTable("a", lvo, new String[]{"age"}, null);
//			System.out.println(result);
		
		try {
			System.out.println(RmObjectMapper.getInstance().readValue("{\"a\":{\"1\":1,\"2\":2},\"b\":3}", HashMap.class).get("a").getClass());
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


class Message {
	public Message(String id, String name, String age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}
	String id;
	String name;
	String age;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}

	
}