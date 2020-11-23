package FinalProject.web;

import java.util.Map;
import java.util.Set;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import FinalProject.beans.Category;
import FinalProject.beans.Coupon;
import FinalProject.exceptions.CustomerAllreadyGotOne;
import FinalProject.exceptions.ExpiredCouponException;
import FinalProject.exceptions.NoCouponsLeft;
import FinalProject.services.CustomerService;
import io.swagger.models.Path;

@RestController
@RequestMapping("customer")
public class CustomerController {

	@Autowired
	Map<String, Session> sessionsMap;

	private ResponseEntity<Object> toReturnStatusUnauthorized() {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("unauthorized login");
	}

	private void isTimeout(Session session, String token) {
		if (session != null) {
			if (System.currentTimeMillis()-session.getLastAccessed()> +1000 * 1000) {
				sessionsMap.remove(token);
				session = null;
			}
		}
	}

	@PutMapping("/purchaseCoupon/{couponID}/{token}")
	public ResponseEntity<Object> CouponPurchase(@PathVariable int couponID, @PathVariable String token) {
		Session session = sessionsMap.get(token);
		isTimeout(session, token);
		if (session != null) {
			session.setLastAccessed(System.currentTimeMillis());
			CustomerService service = (CustomerService) session.getFacade();
			try {
				service.couponPurchase(couponID);
			} catch (ExpiredCouponException | NoCouponsLeft | CustomerAllreadyGotOne e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
			return ResponseEntity.ok(service.getOneCoupon(couponID));
		}
		return toReturnStatusUnauthorized();
	}

	@GetMapping("/showAllCoupons/{token}")
	public ResponseEntity<Object> showAllCoupons(@PathVariable String token) {
		Session session = sessionsMap.get(token);
		isTimeout(session, token);
		if (session != null) {
			session.setLastAccessed(System.currentTimeMillis());
			CustomerService service = (CustomerService) session.getFacade();
			if (service.showAllCoupons()==null)
				return ResponseEntity.badRequest().body("no Coupons Yet!!");
			else 
				return ResponseEntity.ok(service.showAllCoupons());
		}
		return toReturnStatusUnauthorized();
	}
	
	@GetMapping("/getAllCoupons/{token}")
	public ResponseEntity<Object> getAllCoupons(@PathVariable String token) {
		Session session = sessionsMap.get(token);
		isTimeout(session, token);
		if (session != null) {
			session.setLastAccessed(System.currentTimeMillis());
			CustomerService service = (CustomerService) session.getFacade();
			if (service.getAllCoupons()==null)
				return ResponseEntity.badRequest().body("Customer Have No Coupons!");
			else 
				return ResponseEntity.ok(service.getAllCoupons());
		}
		return toReturnStatusUnauthorized();
	}
	
	@GetMapping("/customerCoupons/{min}/{max}/{token}")
	public ResponseEntity<Object> getCustomerCoupons(@PathVariable double min,@PathVariable double max,@PathVariable String token) {
		Session session = sessionsMap.get(token);
		isTimeout(session, token);
		if (session != null) {
			session.setLastAccessed(System.currentTimeMillis());
			CustomerService service = (CustomerService) session.getFacade();
			Set<Coupon> coups=service.getCouponsByPriceLimit(min, max);
			if(coups!=null)
			return ResponseEntity.ok(service.getCouponsByPriceLimit(min, max));
			else 
				return ResponseEntity.badRequest().body("No Coupons In That Range!");
		}
		else 
			return toReturnStatusUnauthorized();
	}
	
	@GetMapping("/customerCoupons/{category}/{token}")
	public ResponseEntity<Object> getCustomerCoupons(@PathVariable Category category,@PathVariable String token) {
		Session session = sessionsMap.get(token);
		isTimeout(session, token);
		if (session != null) {
			session.setLastAccessed(System.currentTimeMillis());
			CustomerService service = (CustomerService) session.getFacade();
			Set<Coupon> coups=service.getCouponsByCategory(category);
			if(coups!=null)
			return ResponseEntity.ok(service.getCouponsByCategory(category));
			else 
				return ResponseEntity.badRequest().body("No Coupons of this category");
		}
		else 
			return toReturnStatusUnauthorized();
	}
	@GetMapping("getCompanyName/{couponID}/{token}")
	public ResponseEntity<Object> getCompanyName(@PathVariable int couponID,@PathVariable String token){
		Session session = sessionsMap.get(token);
		isTimeout(session, token);
		if (session != null) {
			session.setLastAccessed(System.currentTimeMillis());
			CustomerService service = (CustomerService) session.getFacade();
		    return ResponseEntity.ok(service.getCompanyName(couponID));
		}
		else 
			return toReturnStatusUnauthorized(); 
	}
	@DeleteMapping("useCoupon/{couponID}/{token}")
	public ResponseEntity<Object> useCoupon(@PathVariable int couponID,@PathVariable String token){
		Session session = sessionsMap.get(token);
		isTimeout(session, token);
		if (session != null) {
			session.setLastAccessed(System.currentTimeMillis());
			CustomerService service = (CustomerService) session.getFacade();
     		service.useCoupon(couponID);
     		return ResponseEntity.ok("used Coupon!");
		}
		return toReturnStatusUnauthorized();
   }
}