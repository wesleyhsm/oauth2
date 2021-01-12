
package br.com.car.rent.system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.car.rent.system.entity.Car;
import br.com.car.rent.system.repository.car.CarRepositoryQuery;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>, CarRepositoryQuery {

	public Optional<Car> findByBoard(@Param("board") final String board);

}
