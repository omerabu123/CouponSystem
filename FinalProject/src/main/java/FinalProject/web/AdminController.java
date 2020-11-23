package FinalProject.web;

import java.util.ArrayList;
import java.util.List;
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
import FinalProject.beans.Company;
import FinalProject.beans.Coupon;
import FinalProject.beans.Customer;
import FinalProject.exceptions.CannotAddCompanyWithSameNameOrEmail;
import FinalProject.exceptions.CannotAddCustomerWithSameEmail;
import FinalProject.exceptions.CannotDeleteCompany;
import FinalProject.exceptions.CannotUpdateCompany;
import FinalProject.exceptions.nameIsNull;
import FinalProject.services.AdminService;

@RestController
@RequestMapping("admin")
public class AdminController {

	@Autowired
	Map<String, Session> sessionsMap;

	private ResponseEntity<Object> toReturnStatusUnauthorized() {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("unauthorized login");
	}

	private void isTimeout(Session session, String token) {
		if (session != null) {
			if (System.currentTimeMillis() - session.getLastAccessed() >= 1000 * 10000) {
				sessionsMap.remove(token);
				session = null;
			}
		}
	}

	@PostMapping("/addCompany/{token}")
	public ResponseEntity<Object> addNewCompany(@PathVariable String token, @RequestBody Company company) {
		Session session = sessionsMap.get(token);
		isTimeout(session, token);
		if (session != null) {
			session.setLastAccessed(System.currentTimeMillis());
			AdminService service = (AdminService) session.getFacade();
			try {
				service.addCompany(company);
			} catch (CannotAddCompanyWithSameNameOrEmail | nameIsNull e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
			return ResponseEntity.ok(service.getOneCompanyByMail(company.getEmail()));
		} else {
			return toReturnStatusUnauthorized();
		}
	}

	@PutMapping("/updateCompany/{token}")
	public ResponseEntity<Object> updateCompany(@PathVariable String token, @RequestBody Company company) {
		Session session = sessionsMap.get(token);
		isTimeout(session, token);
		if (session != null) {
			session.setLastAccessed(System.currentTimeMillis());
			AdminService service = (AdminService) session.getFacade();
			try {
				service.updateCompany(company);
			} catch (CannotUpdateCompany e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
			return ResponseEntity.ok(company);
		} else {
			return toReturnStatusUnauthorized();
		}
	}

	@DeleteMapping("/deleteCompany/{id}/{token}")
	public ResponseEntity<Object> deleteCompany(@PathVariable("id") int companyID, @PathVariable String token) {
		Session session = sessionsMap.get(token);
		isTimeout(session, token);
		if (session != null) {
			session.setLastAccessed(System.currentTimeMillis());
			AdminService service = (AdminService) session.getFacade();
			try {
				service.deleteCompany(service.getOneCompany(companyID));
			} catch (CannotDeleteCompany e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
			return ResponseEntity.ok().body("Company Deleted");
		} else {
			return toReturnStatusUnauthorized();
		}

	}

	@GetMapping("/getAllCompanies/{token}")
	public ResponseEntity<Object> getAllCompanies(@PathVariable String token) {
		Session session = sessionsMap.get(token);
		isTimeout(session, token);
		if (session != null) {
			session.setLastAccessed(System.currentTimeMillis());
			AdminService service = (AdminService) session.getFacade();
			return ResponseEntity.ok(service.getAllCompanies());
		} else
			return toReturnStatusUnauthorized();
	}

	@GetMapping("/getOneCompany/{id}/{token}")
	public ResponseEntity<Object> getOneCompany(@PathVariable int id, @PathVariable String token) {
		Session session = sessionsMap.get(token);
		isTimeout(session, token);
		if (session != null) {
			session.setLastAccessed(System.currentTimeMillis());
			AdminService service = (AdminService) session.getFacade();
			if (service.getOneCompany(id) == null) {
				return ResponseEntity.badRequest().body("No company with such id!");
			}
			return ResponseEntity.ok(service.getOneCompany(id));
		} else
			return toReturnStatusUnauthorized();
	}

	@PostMapping("addCustomer/{token}")
	public ResponseEntity<Object> addCustomer(@PathVariable String token, @RequestBody Customer customer) {
		Session session = sessionsMap.get(token);
		isTimeout(session, token);
		if (session != null) {
			session.setLastAccessed(System.currentTimeMillis());
			AdminService service = (AdminService) session.getFacade();
			try {
				service.addCustomer(customer);
			} catch (CannotAddCustomerWithSameEmail e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
			return ResponseEntity.ok().body(customer);
		} else
			return toReturnStatusUnauthorized();
	}

	@PutMapping("updateCustomer/{token}")
	public ResponseEntity<Object> updateCustomer(@PathVariable String token, @RequestBody Customer customer) {
		Session session = sessionsMap.get(token);
		isTimeout(session, token);
		if (session != null) {
			session.setLastAccessed(System.currentTimeMillis());
			AdminService service = (AdminService) session.getFacade();
			service.updateCustomer(customer);
			return ResponseEntity.ok().body(customer);
		} else
			return toReturnStatusUnauthorized();
	}

	@DeleteMapping("deleteCustomer/{customerID}/{token}")
	public ResponseEntity<Object> deleteCustomer(@PathVariable int customerID, @PathVariable String token) {
		Session session = sessionsMap.get(token);
		isTimeout(session, token);
		if (session != null) {
			session.setLastAccessed(System.currentTimeMillis());
			AdminService service = (AdminService) session.getFacade();
			service.deleteCustomer(service.getOneCustomer(customerID));
			return ResponseEntity.ok().body("customer deleted");
		} else
			return toReturnStatusUnauthorized();
	}

	@GetMapping("/getAllCustomers/{token}")
	public ResponseEntity<Object> getAllCustomers(@PathVariable String token) {
		Session session = sessionsMap.get(token);
		isTimeout(session, token);
		if (session != null) {
			session.setLastAccessed(System.currentTimeMillis());
			AdminService service = (AdminService) session.getFacade();
			return ResponseEntity.ok(service.getAllCustomers());
		} else
			return toReturnStatusUnauthorized();
	}

	@GetMapping("/getOneCustomer/{id}/{token}")
	public ResponseEntity<Object> getOneCustomer(@PathVariable int id, @PathVariable String token) {
		Session session = sessionsMap.get(token);
		isTimeout(session, token);
		if (session != null) {
			session.setLastAccessed(System.currentTimeMillis());
			AdminService service = (AdminService) session.getFacade();
			if (service.getOneCustomer(id) == null) {
				return ResponseEntity.badRequest().body("No customer with such id!");
			}
			return ResponseEntity.ok(service.getOneCustomer(id));
		} else
			return toReturnStatusUnauthorized();
	}

}