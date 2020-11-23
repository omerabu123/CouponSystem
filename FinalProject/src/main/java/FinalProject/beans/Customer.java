package FinalProject.beans;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "Customers")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerID;
	@Column
	private String customerFirstName;
	@Column
	private String customerLastName;
	@Column
	private String email;
	@Column
	private String password;
	@ManyToMany (fetch = FetchType.EAGER)
	private Set<Coupon> customerCoupons;
	public Customer() {}
	public Customer(int customerID, String customerFirstName, String customerLastName, String email, String password,
			Set<Coupon> customerCoupons) {
		super();
		this.customerID = customerID;
		this.customerFirstName = customerFirstName;
		this.customerLastName = customerLastName;
		this.email = email;
		this.password = password;
		this.customerCoupons = customerCoupons;
	}
	public Customer(String customerFirstName, String customerLastName, String email, String password,
			Set<Coupon> customerCoupons) {
		super();
		this.customerFirstName = customerFirstName;
		this.customerLastName = customerLastName;
		this.email = email;
		this.password = password;
		this.customerCoupons = customerCoupons;
	}
	public String getCustomerFirstName() {
		return customerFirstName;
	}
	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = customerFirstName;
	}
	public String getCustomerLastName() {
		return customerLastName;
	}
	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<Coupon> getCustomerCoupons() {
		return customerCoupons;
	}
	public void setCustomerCoupons(Set<Coupon> coupons) {
		this.customerCoupons = coupons;
	}
	public int getCustomerID() {
		return customerID;
	}
	@Override
	public String toString() {
		return "Customer [customerID=" + customerID + ", customerFirstName=" + customerFirstName + ", customerLastName="
				+ customerLastName + ", email=" + email + ", password=" + password + ", customerCoupons="
				+ customerCoupons + "]";
	}
	
}
