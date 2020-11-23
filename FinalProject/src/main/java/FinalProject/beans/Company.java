package FinalProject.beans;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import FinalProject.beans.Coupon;

@Entity
@Table(name = "Companies")
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int companyID;
	@Column
	private String companyName;
	@Column
	private String email;
	@Column
	private String password;
	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Coupon> companyCoupons;


	public Company() {
	}

	public Company(int companyID, String companyName, String email, String password, Set<Coupon> companyCoupons) {
		super();
		this.companyID = companyID;
		this.companyName = companyName;
		this.email = email;
		this.password = password;
		this.companyCoupons = companyCoupons;
	}

	public Company(String companyName, String email, String password, Set<Coupon> companyCoupons) {
		super();
		this.companyName = companyName;
		this.email = email;
		this.password = password;
		this.companyCoupons = companyCoupons;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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

		this.password=password;
	}

	public Set<Coupon> getCompanyCoupons() {
		return companyCoupons;
	}

	public void setCompanyCoupons(Set<Coupon> companyCoupons) {
		this.companyCoupons = companyCoupons;
	}

	public int getCompanyID() {
		return companyID;
	}

	@Override
	public String toString() {
		return "Company [companyID=" + companyID + ", companyName=" + companyName + ", email=" + email + ", password="
				+ password + ", companyCoupons=" + companyCoupons + "]";
	}

}
