package FinalProject.web;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import FinalProject.exceptions.FailedLogin;
import FinalProject.services.ClientType;
import FinalProject.services.LoginManager;
import FinalProject.services.ClientServices;

@RestController
public class LoginController {

	@Autowired
	private LoginManager loginManager;
	@Autowired
	private Map<String, Session> sessionsMap;

	@PostMapping("/login/{email}/{password}/{type}")
	public String login(@PathVariable String email, @PathVariable String password, @PathVariable ClientType type) {

		String token = UUID.randomUUID().toString();
		ClientServices service;
		try {
			service = loginManager.Login(type, email, password);
			if (service!=null) {
			Session session = new Session(service, System.currentTimeMillis());
			sessionsMap.put(token, session);
			return token;
			}
			return "Error: login failed";
		} catch (FailedLogin e ) {
			return "Error: login failed";
		}
	}
	@PostMapping("/logout/{token}")
	public void logout(@PathVariable String token) {
		sessionsMap.remove(token);
	}
}
