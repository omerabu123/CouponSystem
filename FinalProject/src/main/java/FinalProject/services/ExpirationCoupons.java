package FinalProject.services;

import FinalProject.beans.Coupon;
import FinalProject.jpaRepositories.CouponDBDAO;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExpirationCoupons implements Runnable {

	@Autowired
	private CouponDBDAO couponDB;
	@Autowired
	private CompanyService compService;
	private Set<Coupon> coupons;
	private boolean flag = true;

	@Override
	public void run() {
		while (flag) {
			coupons = couponDB.GetAllCoupons();
			for (Coupon coupon : coupons) {
				if (coupon.getExpDate().getTime() <= Calendar.getInstance().getTimeInMillis()) {
					compService.deleteCoupon(coupon);
					System.out.println("coupon Successfully deleted");
				}
			}

			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void quit() {
		flag = false;
	}

	public void start() {
		flag = true;
	}

}
