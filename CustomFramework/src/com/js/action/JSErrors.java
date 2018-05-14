	package com.js.action;
	
	import java.util.HashMap;
import java.util.Set;
	
	public class JSErrors {
		private HashMap<String, JSError> errors = new HashMap<String, JSError>();
	
		public void addErrors(String key, JSError error) {
			errors.put(key, error);
		}
	
		public Set getKey() {
			Set<String> keyset = errors.keySet();
			return keyset;
		}
		
		public int getElement()
		{
			return errors.size();
		}
		
		public JSError getError(String key)
		{
			return errors.get(key);
		}
	}
