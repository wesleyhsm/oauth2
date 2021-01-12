
package br.com.car.rent.system.busines.test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.car.rent.system.business.CarBusiness;
import br.com.car.rent.system.dto.CarDto;
import br.com.car.rent.system.entity.Car;
import br.com.car.rent.system.exception.CheckBoardCarException;
import br.com.car.rent.system.exception.VerifyCarException;
import br.com.car.rent.system.repository.CarRepository;

@RunWith(MockitoJUnitRunner.class)
public class CarBusinessTest {

	@InjectMocks
	private CarBusiness carBusiness;

	@Mock
	private CarRepository carRepository;

	private final Date date = new Date();

	@Test
	public void saveCarTest() throws ParseException {
		final CarDto carDto = createCarDto();
		final Car carCreate = createCar();

		Mockito.when(carBusiness.saveCar(carDto)).thenReturn(carCreate);
		Mockito.when(carRepository.findByBoard(carDto.getBoard())).thenReturn(null);

		final Car car = carBusiness.saveCar(carDto);

		Assert.assertEquals(carDto.getBoard(), car.getBoard());
		Assert.assertEquals(carDto.getBrand(), car.getBrand());
		Assert.assertEquals(carDto.getModel(), car.getModel());
		Assert.assertEquals(carDto.getPrice(), car.getPrice());
		Assert.assertEquals(carDto.getYear(), car.getYear());
	}

	@Test(expected = CheckBoardCarException.class)
	public void saveCarTest2() throws ParseException {
		final CarDto carDto = createCarDto2();
		final Car carCreate = createCar();

		Mockito.when(carBusiness.saveCar(carDto)).thenReturn(carCreate);
		Mockito.when(carRepository.findByBoard(carDto.getBoard())).thenReturn(null);

		final Car car = carBusiness.saveCar(carDto);

		Assert.assertEquals(carDto.getBoard(), car.getBoard());
		Assert.assertEquals(carDto.getBrand(), car.getBrand());
		Assert.assertEquals(carDto.getModel(), car.getModel());
		Assert.assertEquals(carDto.getPrice(), car.getPrice());
		Assert.assertEquals(carDto.getYear(), car.getYear());
	}

	@Test(expected = VerifyCarException.class)
	public void saveCarTest3() throws ParseException {
		final CarDto carDto = createCarDto();
		final Car carCreate = createCar();
		final Optional<Car> carCreateOptional = createCarOptional();

		Mockito.when(carBusiness.saveCar(carDto)).thenReturn(carCreate);
		Mockito.when(carRepository.findByBoard(carDto.getBoard())).thenReturn(carCreateOptional);

		final Car car = carBusiness.saveCar(carDto);

		Assert.assertEquals(carDto.getBoard(), car.getBoard());
		Assert.assertEquals(carDto.getBrand(), car.getBrand());
		Assert.assertEquals(carDto.getModel(), car.getModel());
		Assert.assertEquals(carDto.getPrice(), car.getPrice());
		Assert.assertEquals(carDto.getYear(), car.getYear());
	}

	@Test
	public void searchAllCarTest() {
		final List<CarDto> carDtos = createCarDtos();
		final List<Car> cars = createCars();

		Mockito.when(carBusiness.searchAllCar()).thenReturn(carDtos);
		Mockito.when(carRepository.findAll()).thenReturn(cars);

		final List<Car> carsRepository = carRepository.findAll();

		Assert.assertFalse(carDtos.isEmpty());
		Assert.assertFalse(cars.isEmpty());
		Assert.assertFalse(carsRepository.isEmpty());
		Assert.assertEquals(carsRepository.get(0).getBoard(), carDtos.get(0).getBoard());
		Assert.assertEquals(carsRepository.get(0).getBrand(), carDtos.get(0).getBrand());
		Assert.assertEquals(carsRepository.get(0).getModel(), carDtos.get(0).getModel());
		Assert.assertEquals(carsRepository.get(0).getPrice(), carDtos.get(0).getPrice());
		Assert.assertEquals(carsRepository.get(0).getYear(), carDtos.get(0).getYear());
	}

	@Test
	public void removeCarTest() {
		final String board = "byt-5487";
		final Optional<Car> carCreate = createCarOptional();

		Mockito.when(carRepository.findByBoard(board)).thenReturn(carCreate);
		carBusiness.removeCar(board);
	}

	@Test(expected = CheckBoardCarException.class)
	public void removeCarTest2() {
		final String board = "byt35487";
		final Optional<Car> carCreate = createCarOptional();

		Mockito.when(carRepository.findByBoard(board)).thenReturn(carCreate);
		carBusiness.removeCar(board);
	}

	private CarDto createCarDto() {
		final BigDecimal bigDecimal = new BigDecimal(8484.85);
		return new CarDto("brand", "model", "boa-7474", bigDecimal, date);
	}

	private CarDto createCarDto2() {
		final BigDecimal bigDecimal = new BigDecimal(8484.85);
		return new CarDto("brand", "model", "boar7474", bigDecimal, date);
	}

	private List<CarDto> createCarDtos() {
		return Arrays.asList(createCarDto());
	}

	private Car createCar() {
		final BigDecimal bigDecimal = new BigDecimal(8484.85);
		return new Car(Long.valueOf(10L), "brand", "model", "boa-7474", bigDecimal, date);
	}

	private List<Car> createCars() {
		return Arrays.asList(createCar());
	}

	private Optional<Car> createCarOptional() {
		return Optional.of(createCar());
	}
}
