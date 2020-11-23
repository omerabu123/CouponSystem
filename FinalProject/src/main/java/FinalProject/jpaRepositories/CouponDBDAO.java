package FinalProject.jpaRepositories;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import FinalProject.beans.Category;
import FinalProject.beans.Company;
import FinalProject.beans.Coupon;
import FinalProject.beans.Customer;

@Repository
public class CouponDBDAO {

	@Autowired
	private CouponRepository repo;
	@Autowired
	private CustomerDBDAO customerDB;

	public void AddCoupon(Coupon c) {
		repo.save(c);
	}

	public void DeleteCoupon(Coupon c) {
		repo.delete(c);
	}

	public void UpdateCoupon(Coupon c) {
		repo.save(c);
	}
	public Coupon GetOneCoupon(int couponID) {
		Optional<Coupon> opt = repo.findById(couponID);
		if (opt.isPresent())
			return opt.get();
		else
			return null;
	}

	public Set<Coupon> GetAllCoupons() {
		Set<Coupon> s=new HashSet<Coupon>();
		s.addAll(repo.findAll());
		return s;
	}

	public void AddCouponPurchase(int customerID, int couponID) {
		Customer c = customerDB.GetOneCustomer(customerID);
		Coupon c1 = GetOneCoupon(couponID);
		Set<Coupon> coupons = c.getCustomerCoupons();
		coupons.add(c1);
		c.setCustomerCoupons(coupons);
	}

	public void DeleteCouponPurchase(int customerID, int couponID) {
		Customer c = customerDB.GetOneCustomer(customerID);
		Coupon c1 = GetOneCoupon(couponID);
		Set<Coupon> coupons = c.getCustomerCoupons();
		coupons.remove(c1);
		c.setCustomerCoupons(coupons);
	}

	public Set<Coupon> getCouponsByPrice(double d, double e) {
		Set<Coupon> s=new HashSet<Coupon>();
		s.addAll(repo.findCouponsByCouponPriceBetween(d, e));
		return s;
	}

	public Set<Coupon> getCouponsByCategory(Category category) {
		Set<Coupon> s=new HashSet<Coupon>();
		s.addAll(repo.findCouponsByCouponCategory(category));
		return s;
	}

}
