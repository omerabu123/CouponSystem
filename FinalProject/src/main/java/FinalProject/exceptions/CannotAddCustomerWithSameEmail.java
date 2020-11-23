package FinalProject.exceptions;

public class CannotAddCustomerWithSameEmail extends Exception {

	public CannotAddCustomerWithSameEmail() {
		super("cannot add customer! email allready exist!");
	}
}
