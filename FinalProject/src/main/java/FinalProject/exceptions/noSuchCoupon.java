package FinalProject.exceptions;

public class noSuchCoupon extends Exception {

	public noSuchCoupon() {
		super("there is no coupon with this id number!");
	}
}
