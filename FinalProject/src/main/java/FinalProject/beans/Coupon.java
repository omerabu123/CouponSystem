package FinalProject.beans;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Coupons")
public class Coupon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int couponID;
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	private Company companyID;
	@Column
	private Category couponCategory;
	@Column
	private String couponTitle;
	@Column
	private String couponDesc;
	@Column
	private Date startDate;
	@Column
	private Date expDate;
	@Column
	private int couponAmount;
	@Column
	private double couponPrice;
	@Column
	private String imageCoupon;
	public Coupon() {}
	public Coupon(int couponID, Company companyID, Category couponCategory, String couponTitle, String couponDesc,
			Date startDate, Date expDate, int couponAmount, double couponPrice, String imageCoupon) {
		super();
		this.couponID = couponID;
		this.companyID = companyID;
		this.couponCategory = couponCategory;
		this.couponTitle = couponTitle;
		this.couponDesc = couponDesc;
		this.startDate = startDate;
		this.expDate = expDate;
		this.couponAmount = couponAmount;
		this.couponPrice = couponPrice;
		this.imageCoupon = imageCoupon;
	}

	
	public Coupon(Company companyID, Category couponCategory, String couponTitle, String couponDesc, Date startDate,
			Date expDate, int couponAmount, double couponPrice, String imageCoupon) {
		super();
		this.companyID = companyID;
		this.couponCategory = couponCategory;
		this.couponTitle = couponTitle;
		this.couponDesc = couponDesc;
		this.startDate = startDate;
		this.expDate = expDate;
		this.couponAmount = couponAmount;
		this.couponPrice = couponPrice;
		this.imageCoupon = imageCoupon;
	}
	
	public Coupon(int couponID2, Category couponCategory2, String couponTitle2, String couponDesc2, Date startDate2,
			Date expDate2, int couponAmount2, double couponPrice2, String imageCoupon2) {
		// TODO Auto-generated constructor stub
	}
	public void setCompanyID(Company companyID) {
		this.companyID=companyID;
	}
	public int getCouponID() {
		return couponID;
	}
	public void setCouponID(int couponID) {
		this.couponID = couponID;
	}
	public Category getCouponCategory() {
		return couponCategory;
	}
	public void setCouponCategory(Category couponCategory) {
		this.couponCategory = couponCategory;
	}
	public String getCouponTitle() {
		return couponTitle;
	}
	public void setCouponTitle(String couponTitle) {
		this.couponTitle = couponTitle;
	}
	public String getCouponDesc() {
		return couponDesc;
	}
	public void setCouponDesc(String couponDesc) {
		this.couponDesc = couponDesc;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getExpDate() {
		return expDate;
	}
	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}
	public int getCouponAmount() {
		return couponAmount;
	}
	public void setCouponAmount(int couponAmount) {
		this.couponAmount = couponAmount;
	}
	public double getCouponPrice() {
		return couponPrice;
	}
	public void setCouponPrice(double couponPrice) {
		this.couponPrice = couponPrice;
	}
	public String getImageCoupon() {
		return imageCoupon;
	}
	public void setImageCoupon(String imageCoupon) {
		this.imageCoupon = imageCoupon;
	}
	public Company getCompany() {
		return companyID;
	}
	@Override
	public int hashCode() {
		return this.getCouponID();
	}
	@Override
	public boolean equals(Object obj) {
		Coupon c=(Coupon) obj;
		if (c.getCouponID()==this.couponID)
			return true;
		if (c.getCouponTitle()==this.couponTitle)
			return true;
		return false;
	}
	@Override
	public String toString() {
		return "Coupon [couponID=" + couponID + ", companyID=" + companyID + ", couponCategory=" + couponCategory
				+ ", couponTitle=" + couponTitle + ", couponDesc=" + couponDesc + ", startDate=" + startDate
				+ ", expDate=" + expDate + ", couponAmount=" + couponAmount + ", couponPrice=" + couponPrice
				+ ", imageCoupon=" + imageCoupon + "]";
	}
     
}
