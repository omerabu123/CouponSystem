package FinalProject.services;

import java.util.Calendar;
import java.util.Set;

import org.springframework.stereotype.Service;

import FinalProject.exceptions.CustomerAllreadyGotOne;
import FinalProject.exceptions.ExpiredCouponException;
import FinalProject.exceptions.NoCouponsLeft;
import FinalProject.beans.Category;
import FinalProject.beans.Coupon;
import FinalProject.beans.Customer;

@Service
public class CustomerService extends ClientServices {

	private int customerID;

	public boolean Login(String email, String password) {
		if (customerDB.IsCustomerExists(email, password)) {
			customerID = customerDB.getCustomerByEmailAndPassowrd(email, password).getCustomerID();
			return true;
		} else
			return false;
	}
	public Set<Coupon> showAllCoupons(){
		return couponDB.GetAllCoupons();
	}
	public String getCompanyName(int couponID) {
		return couponDB.GetOneCoupon(couponID).getCompany().getCompanyName();
	}
	
	public Coupon getOneCoupon(int couponID) {
		return couponDB.GetOneCoupon(couponID);
	}

	public Customer getCustomerDetails() {
		return customerDB.GetOneCustomer(customerID);
	}

	public int getCustomerID() {
		return customerID;
	}

	public Set<Coupon> getAllCoupons() {
		return customerDB.getCustomerCoupons(customerID);
	}

	public void couponPurchase(int couponid) throws ExpiredCouponException, NoCouponsLeft, CustomerAllreadyGotOne {
		Coupon c = couponDB.GetOneCoupon(couponid);
		if (c.getExpDate().before(Calendar.getInstance().getTime()))
			throw new ExpiredCouponException();
		if (c.getCouponAmount() == 0)
			throw new NoCouponsLeft();
		Set<Coupon> coupons = getAllCoupons();
		for (Coupon coupon : coupons) {
			if (c.getCouponID()==coupon.getCouponID())
				throw new CustomerAllreadyGotOne();
		}
		c.setCouponAmount(c.getCouponAmount() - 1);
		coupons.add(c);
		Customer cust = customerDB.GetOneCustomer(customerID);
		cust.setCustomerCoupons(coupons);
		customerDB.UpdateCustomer(cust);
	}

	public Set<Coupon> getCouponsByCategory(Category category) {
		return customerDB.getCustomerCouponsByCategory(category, customerID);
	}

	public Set<Coupon> getCouponsByPriceLimit(double min, double max) {
		return customerDB.getCustomerCouponsByPrice(min, max, customerID);
	}
	public void useCoupon(int couponID) {
		getCustomerDetails().getCustomerCoupons().remove(getOneCoupon(couponID));
		customerDB.UpdateCustomer(getCustomerDetails());
	}
}
