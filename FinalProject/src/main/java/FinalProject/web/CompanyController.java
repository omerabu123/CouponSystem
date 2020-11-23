package FinalProject.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import FinalProject.beans.Category;
import FinalProject.beans.Coupon;
import FinalProject.exceptions.CompanyAllreadyHaveThisCoupon;
import FinalProject.exceptions.noSuchCoupon;
import FinalProject.services.CompanyService;

@RestController
@RequestMapping("company")
public class CompanyController {

	@Autowired
	Map<String, Session> sessionsMap;

	private ResponseEntity<Object> toReturnStatusUnauthorized() {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("unauthorized login");
	}

	private void isTimeout(Session session, String token) {
		if (session != null) {
			if (System.currentTimeMillis() - session.getLastAccessed() > +1000 * 1000) {
				sessionsMap.remove(token);
				session = null;
			}
		}
	}

	@PostMapping("/addCoupon/{token}")
	public ResponseEntity<Object> addCoupon(@PathVariable String token, @RequestBody Coupon coupon) {
		Session session = sessionsMap.get(token);
		isTimeout(session, token);
		if (session != null) {
			session.setLastAccessed(System.currentTimeMillis());
			CompanyService service = (CompanyService) session.getFacade();
			Coupon c = new Coupon(service.getCompanyDetails(), coupon.getCouponCategory(), coupon.getCouponTitle(),
					coupon.getCouponDesc(), coupon.getStartDate(), coupon.getExpDate(), coupon.getCouponAmount(),
					coupon.getCouponPrice(), coupon.getImageCoupon());
			try {
				service.addCoupon(c);
			} catch (CompanyAllreadyHaveThisCoupon e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
			return ResponseEntity.ok(service.getCouponByName(c.getCouponTitle()));
		} else
			return toReturnStatusUnauthorized();
	}

	@PutMapping("/updateCoupon/{token}")
	public ResponseEntity<Object> updateCoupon(@PathVariable String token, @RequestBody Coupon coupon) {
		Session session = sessionsMap.get(token);
		isTimeout(session, token);
		if (session != null) {
			session.setLastAccessed(System.currentTimeMillis());
			CompanyService service = (CompanyService) session.getFacade();
			service.updateCoupon(coupon);
			return ResponseEntity.ok(coupon);
		} else
			return toReturnStatusUnauthorized();
	}

	@DeleteMapping("/deleteCoupon/{id}/{token}")
	public ResponseEntity<Object> updateCoupon(@PathVariable int id, @PathVariable String token) {
		Session session = sessionsMap.get(token);
		isTimeout(session, token);
		if (session != null) {
			session.setLastAccessed(System.currentTimeMillis());
			CompanyService service = (CompanyService) session.getFacade();
			try {
				service.deleteCoupon(service.getOneCoupon(id));
			} catch (noSuchCoupon e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
			return ResponseEntity.ok().body("Coupon Deleted!");
		} else
			return toReturnStatusUnauthorized();
	}

	@GetMapping("/companyCoupons/{token}")
	public ResponseEntity<Object> getCompanyCoupons(@PathVariable String token) {
		Session session = sessionsMap.get(token);
		isTimeout(session, token);
		if (session != null) {
			session.setLastAccessed(System.currentTimeMillis());
			CompanyService service = (CompanyService) session.getFacade();
			return ResponseEntity.ok(service.getCompanyCoupons());
		} else
			return toReturnStatusUnauthorized();
	}

	@GetMapping("/companyCoupons/{category}/{token}")
	public ResponseEntity<Object> getCompanyCoupons(@PathVariable Category category, @PathVariable String token) {
		Session session = sessionsMap.get(token);
		isTimeout(session, token);
		if (session != null) {
			session.setLastAccessed(System.currentTimeMillis());
			CompanyService service = (CompanyService) session.getFacade();
			return ResponseEntity.ok(service.getCompanyCouponsByCategory(category));
		} else
			return toReturnStatusUnauthorized();
	}

	@GetMapping("/companyCoupons/{min}/{max}/{token}")
	public ResponseEntity<Object> getCompanyCoupons(@PathVariable double min, @PathVariable double max,
			@PathVariable String token) {
		Session session = sessionsMap.get(token);
		isTimeout(session, token);
		if (session != null) {
			session.setLastAccessed(System.currentTimeMillis());
			CompanyService service = (CompanyService) session.getFacade();
			return ResponseEntity.ok(service.getCompanyCouponsByPrice(min, max));
		} else
			return toReturnStatusUnauthorized();
	}

	@GetMapping("/getOneCoupon/{id}/{token}")
	public ResponseEntity<Object> getOneCoupon(@PathVariable int id,@PathVariable String token){
		Session session = sessionsMap.get(token);
		isTimeout(session, token);
		if (session != null) {
			session.setLastAccessed(System.currentTimeMillis());
			CompanyService service = (CompanyService) session.getFacade();
			try {
				return ResponseEntity.ok(service.getOneCoupon(id));
			} catch (noSuchCoupon e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
		}
		return toReturnStatusUnauthorized();
	}
	@GetMapping("/companyDetails/{token}")
	public ResponseEntity<Object> getCompanyDetails(@PathVariable String token) {
		Session session = sessionsMap.get(token);
		isTimeout(session, token);
		if (session != null) {
			session.setLastAccessed(System.currentTimeMillis());
			CompanyService service = (CompanyService) session.getFacade();
			return ResponseEntity.ok(service.getCompanyDetails());
		} else
			return toReturnStatusUnauthorized();
	}
}