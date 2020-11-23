
package FinalProject.services;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import FinalProject.exceptions.CannotAddCompanyWithSameNameOrEmail;
import FinalProject.exceptions.CannotAddCustomerWithSameEmail;
import FinalProject.exceptions.CannotDeleteCompany;
import FinalProject.exceptions.CannotUpdateCompany;
import FinalProject.exceptions.nameIsNull;
import FinalProject.beans.Company;
import FinalProject.beans.Coupon;
import FinalProject.beans.Customer;

@Service
public class AdminService extends ClientServices {

	private String correctEmail = "admin@admin.com";
	private String correctPassword = "admin";

	public AdminService() {
		
	}

	public boolean Login(String email, String password) {
		if (correctEmail.equals(email) && correctPassword.equals(password))
			return true;
		else
			return false;
	}

	public int getOneCompanyByMail(String email) {
		return companyDB.getCompanyIdByMail(email);
	}

	public void addCompany(Company c) throws CannotAddCompanyWithSameNameOrEmail, nameIsNull {
		Set<Company> companies = companyDB.GetAllCompanies();
		if(c.getCompanyName()==null)
		throw new nameIsNull();
		for (Company company : companies) {
			if (company.getCompanyName() == c.getCompanyName())
				throw new CannotAddCompanyWithSameNameOrEmail();
			if (company.getEmail().equals(c.getEmail()))
				throw new CannotAddCompanyWithSameNameOrEmail();
		}
		companyDB.AddCompany(c);
	}

	public void updateCompany(Company c) throws CannotUpdateCompany {
		if (companyDB.GetOneCompany(c.getCompanyID())!=null)
			companyDB.UpdateCompany(c);
		else
			throw new CannotUpdateCompany();
	}

	public void deleteCompany(Company c) throws CannotDeleteCompany {
		for (Coupon coupon : c.getCompanyCoupons()) {
		     for (Customer custs: getAllCustomers()) {
		    	 for (Coupon coups : custs.getCustomerCoupons()) {
					if (coups.equals(coupon))
						custs.getCustomerCoupons().remove(coups);
				}
		        customerDB.UpdateCustomer(custs);
		     }
		}
			companyDB.DeleteCompany(c);
//		else
//			throw new CannotDeleteCompany();
	}

	public Company getOneCompany(int companyID) {
		return companyDB.GetOneCompany(companyID);
	}

	public Set<Company> getAllCompanies() {
		return companyDB.GetAllCompanies();
	}

	public void addCustomer(Customer c) throws CannotAddCustomerWithSameEmail {
		Set<Customer> customers = customerDB.GetAllCustomers();
		for (Customer customer : customers) {
			if (c.getEmail().equals(customer.getEmail()))
				throw new CannotAddCustomerWithSameEmail();
		}
		customerDB.AddCustomer(c);
	}

	public void updateCustomer(Customer c) {
		Set<Customer> customers = customerDB.GetAllCustomers();
		boolean flag = false;
		for (Customer customer : customers) {
			if (customer.getEmail().equals(c.getEmail()))
				customerDB.UpdateCustomer(c);
			flag = true;
			break;
		}
		if (!flag)
			System.out.println("customer dosent exist!");
	}

	public void deleteCustomer(Customer c) {
		c.setCustomerCoupons(null);
		updateCustomer(c);
		customerDB.DeleteCustomer(c);
	}

	public Customer getOneCustomer(int customerID) {
		return customerDB.GetOneCustomer(customerID);
	}

	public Set<Customer> getAllCustomers() {
		return customerDB.GetAllCustomers();
	}
}