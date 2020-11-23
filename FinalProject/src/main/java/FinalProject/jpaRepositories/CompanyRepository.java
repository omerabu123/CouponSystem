package FinalProject.jpaRepositories;


import org.springframework.data.jpa.repository.JpaRepository;

import FinalProject.beans.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer>{

	public Company findCompanyByEmailAndPassword(String email,String password);
	public Company findCompanyByEmail(String email);
	}
