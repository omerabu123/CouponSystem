package FinalProject.services;

import java.util.Set;

import org.springframework.stereotype.Service;

import FinalProject.exceptions.CompanyAllreadyHaveThisCoupon;
import FinalProject.exceptions.noSuchCoupon;
import FinalProject.beans.Category;
import FinalProject.beans.Company;
import FinalProject.beans.Coupon;
import FinalProject.beans.Customer;

@Service
public class CompanyService extends ClientServices {


	private int companyID;

	@Override
	public boolean Login(String email, String password) {
		if (companyDB.IsCompanyExists(email, password)) {
			companyID = companyDB.getCompanyIdByMail(email);
			return true;
		} else
			return false;
	}
	public Coupon getCouponByName(String name) {
		for (Coupon coup:companyDB.GetOneCompany(companyID).getCompanyCoupons()) {
			if (name.equals(coup.getCouponTitle()))
					return coup;
		}
		return null;
		
	}
	public void addCoupon(Coupon c) throws CompanyAllreadyHaveThisCoupon {
		Set<Coupon> companyCoupons = companyDB.getCompanyCoupons(companyID);
		for (Coupon coupon : companyCoupons) {
			if (coupon.equals(c))
				throw new CompanyAllreadyHaveThisCoupon();
		}
		companyCoupons.add(c);
		Company comp = companyDB.GetOneCompany(companyID);
		comp.setCompanyCoupons(companyCoupons);
		companyDB.UpdateCompany(comp);
	}

	public void updateCoupon(Coupon c) {
//		boolean flag = false;
		c.setCompanyID(this.getCompanyDetails());
		Set<Coupon> companyCoupons = companyDB.getCompanyCoupons(companyID);
		for (Coupon coupon : companyCoupons) {
			if (c.equals(coupon)) {
				couponDB.UpdateCoupon(c);
//				flag = true;
				break;
			}
		}
/*		if (flag) {
			System.out.println("coupon updated");
		} else
			System.out.println("coupon could not be updated");*/
	}
	public void deleteCoupon(Coupon c) {
		//checking if the coupon thats going to be deleted owned by this company, if not this company cant delete it. 
		if (c.getCompany().getCompanyID()==companyID) {
		Set<Customer> custs = customerDB.GetAllCustomers();
		for (Customer customer : custs) {
			Set<Coupon> coups = customer.getCustomerCoupons();
			for (Coupon coupon : coups) {
				if (coupon.equals(c)) {
					coups.remove(coupon);
				break;
				}
			}
			customer.setCustomerCoupons(coups);
			customerDB.UpdateCustomer(customer);

		}
		Company comp = companyDB.GetOneCompany(companyID);
		Set<Coupon> newone = comp.getCompanyCoupons();
		newone.remove(c);
		comp.setCompanyCoupons(newone);
		companyDB.UpdateCompany(comp);
		couponDB.DeleteCoupon(c);
		}
		else 
			System.out.println("no premisson for you to delete this coupon");
	}

	public Company getCompanyDetails() {
		return companyDB.GetOneCompany(companyID);
	}

	public Coupon getOneCoupon(int couponID) throws noSuchCoupon {
		Coupon cop = couponDB.GetOneCoupon(couponID);
		if (cop == null)
			throw new noSuchCoupon();
		return cop;
	}

	public Set<Coupon> getCompanyCoupons() {
		return companyDB.getCompanyCoupons(companyID);
	}

	public Set<Coupon> getCompanyCouponsByPrice(double d, double e) {
		return couponDB.getCouponsByPrice(d, e);
	}

	public Set<Coupon> getCompanyCouponsByCategory(Category category) {
		return couponDB.getCouponsByCategory(category);
	}

}
