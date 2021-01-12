
package br.com.car.rent.system.repository.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.car.rent.system.entity.Customer;
import br.com.car.rent.system.filter.UserFilter;

public interface CustomerRepositoryQuery {

	public Page<Customer> searchCustomerFilter(UserFilter userFilter, Pageable pageable);
}
