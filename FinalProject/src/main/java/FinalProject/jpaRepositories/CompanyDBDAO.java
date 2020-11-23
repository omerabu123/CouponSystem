package FinalProject.jpaRepositories;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import FinalProject.beans.Company;
import FinalProject.beans.Coupon;

@Repository
public class CompanyDBDAO {

	@Autowired
	private CompanyRepository repo;

	public void AddCompany(Company c) {
		repo.save(c);
	}

	public void UpdateCompany(Company c) {
		repo.save(c);
	}

	public void DeleteCompany(Company c) {
		repo.delete(c);
	}

	public Company GetOneCompany(int companyID) {
		Optional<Company> opt = repo.findById(companyID);
		if (opt.isPresent())
		{
			return opt.get();
		}else
			return null;
	}

	public Set<Coupon> getCompanyCoupons(int companyID) {
		Company comp=GetOneCompany(companyID);
		if (comp!=null) {
			return comp.getCompanyCoupons();
		}
		return null;
	}

	public boolean IsCompanyExists(String email, String password) {
		Company company = null;
		company = repo.findCompanyByEmailAndPassword(email, password);
		if (company == null)
			return false;
		else
			return true;
	}

	public boolean IsCompanyExistsByEmail(String email) {
		Company company = null;
		company = repo.findCompanyByEmail(email);
		if (company == null)
			return false;
		else
			return true;
	}

	public Set<Company> GetAllCompanies() {
		Set<Company> s=new HashSet<Company>();
		 s.addAll(repo.findAll());
		 return s;
	}

	public int getCompanyIdByMail(String email) {
		return repo.findCompanyByEmail(email).getCompanyID();
	}

}
