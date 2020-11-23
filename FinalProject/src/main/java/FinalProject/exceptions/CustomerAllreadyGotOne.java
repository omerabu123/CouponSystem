package FinalProject.exceptions;

public class CustomerAllreadyGotOne extends Exception {

	public CustomerAllreadyGotOne() {
		super("Customer allready Got this coupon");
	}
}
