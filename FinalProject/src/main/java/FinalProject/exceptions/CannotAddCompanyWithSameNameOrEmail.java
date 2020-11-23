package FinalProject.exceptions;

public class CannotAddCompanyWithSameNameOrEmail extends Exception {

	public CannotAddCompanyWithSameNameOrEmail() {
		super("Cannot ADD Company to the database with the same email or name!");
	}
}
