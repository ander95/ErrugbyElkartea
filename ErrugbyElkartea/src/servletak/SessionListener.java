package servletak;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent event) {
		System.out.println("    a HTTP session is being created");
		
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		System.out.println("    a HTTP session is being destroyed");
		
	}

}
