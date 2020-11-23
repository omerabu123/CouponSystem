package FinalProject.exceptions;

public class CannotUpdateCompany extends Exception {

	public CannotUpdateCompany() {
		super("cannot update company that dosent exists!");
	}
}
