package org.quickbundle.util;

import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class RmKeyCountList<E> {
	private RmSequenceMap<E, Long> mkc = new RmSequenceMap<E, Long>();
	public RmKeyCountList() {
		
	}
	
	public void put(E key, long count) {
		mkc.put(key, count);
	}
	
	public long getMaxCount() {
		long max = 0;
		for (Map.Entry<E, Long> en : mkc.entrySet()) {
			Long count = en.getValue();
			if(max < count.longValue()) {
				max = count.longValue();
			}
		}
		return max;
	}
	
	public long getMaxCount4Bar() {
		long max = getMaxCount();
		if(max < 2) {
			max = 2;
		} else {
			max = (int)(max * 1.2);
		}
		return max;
	}
	
	public String getJsonCount() {
		JSONArray ja = new JSONArray();
		for (Map.Entry<E, Long> en : mkc.entrySet()) {
			Long count = en.getValue();
			ja.add(count);
		}
		return ja.toString();
	}
	
	public String getJsonKey() {
		JSONArray ja = new JSONArray();
		for (Map.Entry<E, Long> en : mkc.entrySet()) {
			E key = en.getKey();
			ja.add(key);
		}
		return ja.toString();
	}
	
	public String getJsonKeyCount() {
		JSONArray ja = new JSONArray();
		for (Map.Entry<E, Long> en : mkc.entrySet()) {
			E key = en.getKey();
			long count = en.getValue();
			JSONObject jo = new JSONObject();
			jo.put("value", count);
			jo.put("text", key);
			ja.add(jo);
		}
		return ja.toString();
	}
}
