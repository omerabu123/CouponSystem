package FinalProject.exceptions;

public class FailedLogin extends Exception {

	public FailedLogin() {
		super("failed to login. check email/password");
	}
}
