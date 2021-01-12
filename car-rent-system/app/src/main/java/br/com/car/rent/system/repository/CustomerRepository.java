
package br.com.car.rent.system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.car.rent.system.entity.Customer;
import br.com.car.rent.system.repository.user.CustomerRepositoryQuery;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>, CustomerRepositoryQuery {

	public Optional<Customer> findByMail(@Param("mail") final String mail);

	public Optional<Customer> findByCpfCnpj(@Param("cpfCnpj") final String cpfCnpj);

}
