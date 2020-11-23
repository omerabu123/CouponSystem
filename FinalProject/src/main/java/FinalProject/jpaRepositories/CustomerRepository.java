package FinalProject.jpaRepositories;

import org.springframework.data.jpa.repository.JpaRepository;

import FinalProject.beans.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	public Customer findCustomerByEmailAndPassword(String email, String password);
}
