
package br.com.car.rent.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.car.rent.system.business.UserBusiness;
import br.com.car.rent.system.dto.Response;
import br.com.car.rent.system.dto.UserDto;
import br.com.car.rent.system.filter.UserFilter;

@RestController
@RequestMapping("${api.path}/customer")
@ResponseStatus(HttpStatus.OK)
public class CustomerController {

	@Autowired
	private UserBusiness userBusiness;

	@GetMapping("/all")
	public Response<List<UserDto>> searchAllUserData() {
		final List<UserDto> userDtos = userBusiness.searchAllUser();
		return new Response<>(userDtos);
	}

	@GetMapping
	public Response<Page<UserDto>> searchUserFilterData(final UserFilter userFilter, final Pageable pageable) {
		final Page<UserDto> userDtos = userBusiness.searchUserFilter(userFilter, pageable);
		return new Response<>(userDtos);
	}

	@DeleteMapping("/{cpfCnpj}")
	public Response<?> removeUserData(@PathVariable("cpfCnpj") final String cpfCnpj) {
		userBusiness.removeUser(cpfCnpj);
		return new Response<>();
	}

	@PutMapping("/{cpfCnpj}")
	public Response<?> editUserData(@PathVariable("cpfCnpj") final String cpfCnpj, @RequestBody final UserDto userDto) {

		final UserDto userDtoResponse = userBusiness.editUser(cpfCnpj, userDto);

		return new Response<>(userDtoResponse);
	}
}
