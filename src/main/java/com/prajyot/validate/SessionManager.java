package com.prajyot.validate;

import java.util.HashMap;
import java.util.Map;
import jakarta.servlet.http.HttpSession;

public class SessionManager {
	static Map<String,HttpSession> activeSession = new HashMap<>();
	
	public static void addSession(String username, HttpSession session) {
		HttpSession oldSession = activeSession.put(username, session);
		if(oldSession != null  && oldSession != session) {
			oldSession.invalidate();
			return;
		}
	}
	
	public static void removeSession(String username){
		activeSession.remove(username);
	}
}
