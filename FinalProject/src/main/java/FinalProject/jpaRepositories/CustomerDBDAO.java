package FinalProject.jpaRepositories;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import FinalProject.beans.Category;
import FinalProject.beans.Coupon;
import FinalProject.beans.Customer;

@Repository
public class CustomerDBDAO {

	@Autowired
	private CustomerRepository repo;

	public void AddCustomer(Customer c) {
		repo.save(c);
	}

	public void DeleteCustomer(Customer c) {
		repo.delete(c);
	}

	public void UpdateCustomer(Customer c) {
		repo.save(c);
	}
	public Customer GetOneCustomer(int customerID) {
		Optional<Customer> opt = repo.findById(customerID);
		if (opt.isPresent())
			return opt.get();
		else
			return null;
	}

	public boolean IsCustomerExists(String email, String password) {
		Customer cust = repo.findCustomerByEmailAndPassword(email, password);
		if (cust == null)
			return false;
		else
			return true;
	}

	public Set<Customer> GetAllCustomers() {
		Set<Customer> s=new HashSet<Customer>();
		s.addAll(repo.findAll());
		return s;
	}

	public Customer getCustomerByEmailAndPassowrd(String email, String password) {
		return repo.findCustomerByEmailAndPassword(email, password);
	}

	public Set<Coupon> getCustomerCoupons(int customerID) {
		Optional<Customer> cust = repo.findById(customerID);
		return cust.get().getCustomerCoupons();
	}

	public Set<Coupon> getCustomerCouponsByCategory(Category category, int customerID) {
		Set<Coupon> coupons = getCustomerCoupons(customerID);
		Set<Coupon> couponsByCat = new HashSet<Coupon>();
		if (coupons != null) {
			for (Coupon coupon : coupons) {
				if (coupon.getCouponCategory().equals(category)) {
					couponsByCat.add(coupon);
				}
			}
		}
		return couponsByCat;
	}

	public Set<Coupon> getCustomerCouponsByPrice(double min, double max, int customerID) {
		Set<Coupon> coupons = getCustomerCoupons(customerID);
		Set<Coupon> couponsByPri = new HashSet<Coupon>();
		for (Coupon coupon : coupons) {
			if (coupon.getCouponPrice() >= min && coupon.getCouponPrice() <= max)
				couponsByPri.add(coupon);
		}
		return couponsByPri;
	}
}
