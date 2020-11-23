package FinalProject.jpaRepositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import FinalProject.beans.Category;
import FinalProject.beans.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {

	public List<Coupon> findCouponsByCouponCategory(Category category);

	public List<Coupon> findCouponsByCouponPriceBetween(double d, double e);

}
