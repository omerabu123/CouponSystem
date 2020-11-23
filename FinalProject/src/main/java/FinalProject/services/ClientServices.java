package FinalProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import FinalProject.jpaRepositories.CompanyDBDAO;
import FinalProject.jpaRepositories.CouponDBDAO;
import FinalProject.jpaRepositories.CustomerDBDAO;

@Service
public abstract class ClientServices {
	@Autowired
	protected CompanyDBDAO companyDB;
	@Autowired
	protected CustomerDBDAO customerDB;
	@Autowired 
	protected  CouponDBDAO couponDB;
	
	public abstract boolean Login(String email,String password);
}
