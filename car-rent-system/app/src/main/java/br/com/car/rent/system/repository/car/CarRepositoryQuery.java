
package br.com.car.rent.system.repository.car;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.car.rent.system.entity.Car;
import br.com.car.rent.system.filter.CarFilter;

public interface CarRepositoryQuery {

	public Page<Car> searchCarFilter(CarFilter carFilter, Pageable pageable);
}
