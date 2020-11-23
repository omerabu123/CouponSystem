package FinalProject.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import FinalProject.exceptions.FailedLogin;

@Component
public class LoginManager {

	@Autowired
	private AdminService admin;
	@Autowired
	private CustomerService customer;
	@Autowired
	private CompanyService company;

	public ClientServices Login(ClientType client, String email, String password) throws FailedLogin {
		switch (client) {
		case Admin:
			if (admin.Login(email, password))
				return admin;
			else
				throw new FailedLogin();
		case Company:
			if (company.Login(email, password))
				return company;
			else
				throw new FailedLogin();
		case Customer:
			if (customer.Login(email, password))
				return customer;
			else
				throw new FailedLogin();
		}
		return null;
	}
}
