
package br.com.car.rent.system.controller;

import java.text.ParseException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.car.rent.system.business.UserBusiness;
import br.com.car.rent.system.dto.Response;
import br.com.car.rent.system.dto.UserDto;

@RestController
@RequestMapping("${api.path}/user")
@ResponseStatus(HttpStatus.OK)
public class UserController {

	@Autowired
	private UserBusiness userBusiness;

	@PostMapping
	public Response<?> saveUserData(@Valid @RequestBody final UserDto userDto) throws ParseException {
		userBusiness.saveUser(userDto);
		return new Response<>();
	}
}
