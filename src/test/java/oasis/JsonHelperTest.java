package oasis;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import oasis.core.helper.JsonHelper;

public class JsonHelperTest {

	@SuppressWarnings("unchecked")
	@Test
	public void testJsonReadValue() {
		
		File jsonFile = new File("C:\\Logs\\artris\\test.json");
		try {
			Map<String, Object> jsonMap = JsonHelper.jsonReadValue(jsonFile, LinkedHashMap.class);
			System.out.println(jsonMap);
			assertTrue(true);
		} catch (IOException e) {
			assertFalse(true);
			e.printStackTrace();
		}
	}

	@Test
	public void testJsonWriteValue() {
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> m1 = new HashMap<String, Object>();
		m1.put("key", "root");
		list.add(m1);

		Map<String, Object> m2 = new HashMap<String, Object>();
		m2.put("key", "seoul");
		list.add(m2);
		
		map.put("treelist", list);
		
		File jsonFile = new File("C:\\Logs\\artris\\test.json");
		try {
			JsonHelper.jsonWriteValue(jsonFile, map);
			assertTrue(true);
		} catch (IOException e) {
			assertFalse(true);
			e.printStackTrace();
		}
	}

}
