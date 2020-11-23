package FinalProject;

import java.util.Calendar;
import java.sql.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import FinalProject.exceptions.CannotAddCompanyWithSameNameOrEmail;
import FinalProject.exceptions.CannotAddCustomerWithSameEmail;
import FinalProject.exceptions.CannotDeleteCompany;
import FinalProject.exceptions.CompanyAllreadyHaveThisCoupon;
import FinalProject.exceptions.CustomerAllreadyGotOne;
import FinalProject.exceptions.ExpiredCouponException;
import FinalProject.exceptions.FailedLogin;
import FinalProject.exceptions.NoCouponsLeft;
import FinalProject.exceptions.noSuchCoupon;
import FinalProject.services.AdminService;
import FinalProject.services.ClientType;
import FinalProject.services.CompanyService;
import FinalProject.services.CustomerService;
import FinalProject.services.ExpirationCoupons;
import FinalProject.services.LoginManager;
import FinalProject.beans.Category;
import FinalProject.beans.Company;
import FinalProject.beans.Coupon;
import FinalProject.beans.Customer;

@SpringBootApplication
public class MainService {
	public static void main(String[] args)
			throws CannotAddCompanyWithSameNameOrEmail, FailedLogin, CannotAddCustomerWithSameEmail,
			CompanyAllreadyHaveThisCoupon, ExpiredCouponException, NoCouponsLeft, CustomerAllreadyGotOne, noSuchCoupon, CannotDeleteCompany {
		ConfigurableApplicationContext ctx = SpringApplication.run(MainService.class, args);
		LoginManager login = ctx.getBean(LoginManager.class);
//	ExpirationCoupons exp=ctx.getBean(ExpirationCoupons.class);
//	Thread t1=new Thread(exp);
//	t1.start();

	}
}
