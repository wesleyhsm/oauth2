
package br.com.car.rent.system.business;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.car.rent.system.dto.CarDto;
import br.com.car.rent.system.entity.Car;
import br.com.car.rent.system.exception.CheckBoardCarException;
import br.com.car.rent.system.exception.CheckYearCarException;
import br.com.car.rent.system.exception.VerifyCarException;
import br.com.car.rent.system.filter.CarFilter;
import br.com.car.rent.system.repository.CarRepository;

@Component
public class CarBusiness {

	@Autowired
	private CarRepository carRepository;

	public Car saveCar(final CarDto carDto) throws ParseException {
		isCheckBoardCar(carDto.getBoard());
		checkYearCar(carDto);

		final Optional<Car> carOptional = carRepository.findByBoard(carDto.getBoard());
		if (carOptional != null) {
			throw new VerifyCarException();
		}

		final Car car = new Car(null,
				carDto.getBrand(),
				carDto.getModel(),
				carDto.getBoard(),
				carDto.getPrice(),
				carDto.getYear());

		return persistDataCar(car);
	}

	public List<CarDto> searchAllCar() {
		final List<Car> cars = carRepository.findAll();
		return converterCarDto(cars);
	}

	public Page<CarDto> searchCarFilter(final CarFilter carFilter, final Pageable pageable) {
		final Page<Car> pageCars = carRepository.searchCarFilter(carFilter, pageable);

		final List<CarDto> carDtos = converterCarDto(pageCars.getContent());

		final Page<CarDto> pageCarDtos = new PageImpl<>(carDtos, pageable, pageCars.getTotalElements());

		return pageCarDtos;
	}

	private List<CarDto> converterCarDto(final List<Car> cars) {
		return cars.parallelStream().map(mapper -> carConverterCarDto(mapper)).collect(Collectors.toList());
	}

	private CarDto carConverterCarDto(final Car car) {
		return new CarDto(car.getBrand(), car.getModel(), car.getBoard(), car.getPrice(), car.getYear());
	}

	private void checkYearCar(final CarDto carDto) {
		final Calendar calendar = Calendar.getInstance();
		final int year = calendar.get(Calendar.YEAR) - 3;

		calendar.setTime(carDto.getYear());
		final int yearCar = calendar.get(Calendar.YEAR);

		if (yearCar <= year) {
			throw new CheckYearCarException();
		}
	}

	private Car persistDataCar(final Car car) {
		return carRepository.save(car);
	}

	@Transactional
	public void removeCar(final String board) {
		isCheckBoardCar(board);

		final Optional<Car> carOptional = carRepository.findByBoard(board);
		final Car car = carOptional.orElseThrow(() -> new UsernameNotFoundException("Placa do carro inválida"));

		carRepository.delete(car);
	}

	@Transactional
	public CarDto editCar(final String board, final CarDto carDto) {
		isCheckBoardCar(carDto.getBoard());
		checkYearCar(carDto);

		final Optional<Car> carOptional = carRepository.findByBoard(board);
		final Car car = carOptional.orElseThrow(() -> new UsernameNotFoundException("Placa do carro inválida"));

		final Car carResponse = persistDataCar(new Car(car.getCarId(),
				carDto.getBrand(),
				carDto.getModel(),
				carDto.getBoard(),
				carDto.getPrice(),
				carDto.getYear()));

		return carConverterCarDto(carResponse);
	}

	private void isCheckBoardCar(final String board) {
		if (!verifyBoardCar(board)) {
			throw new CheckBoardCarException();
		}
	}

	private boolean verifyBoardCar(final String board) {
		final Pattern pattern = Pattern.compile("[a-zA-Z]{3,3}-\\d{4,4}");
		final Matcher matcher = pattern.matcher(board);

		if (matcher.find()) {
			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}
}
