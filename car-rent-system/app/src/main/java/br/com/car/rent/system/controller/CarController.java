
package br.com.car.rent.system.controller;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.car.rent.system.business.CarBusiness;
import br.com.car.rent.system.dto.CarDto;
import br.com.car.rent.system.dto.Response;
import br.com.car.rent.system.filter.CarFilter;

@RestController
@RequestMapping("${api.path}/car")
@ResponseStatus(HttpStatus.OK)
public class CarController {

	@Autowired
	private CarBusiness carBusiness;

	@PostMapping
	public Response<?> saveCarData(@Valid @RequestBody final CarDto carDto) throws ParseException {
		carBusiness.saveCar(carDto);
		return new Response<>();
	}

	@GetMapping("/all")
	public Response<List<CarDto>> searchAllCarData() {
		final List<CarDto> carDtos = carBusiness.searchAllCar();
		return new Response<>(carDtos);
	}

	@GetMapping
	public Response<Page<CarDto>> searchCarFilterData(final CarFilter carFilter, final Pageable pageable) {
		final Page<CarDto> carDtos = carBusiness.searchCarFilter(carFilter, pageable);
		return new Response<>(carDtos);
	}

	@DeleteMapping("/{board}")
	public Response<?> removeCarData(@PathVariable("board") final String board) {
		carBusiness.removeCar(board);
		return new Response<>();
	}

	@PutMapping("/{board}")
	public Response<?> editCarData(@PathVariable("board") final String board, @RequestBody final CarDto carDto) {

		final CarDto carDtoResponse = carBusiness.editCar(board, carDto);
		return new Response<>(carDtoResponse);
	}
}
