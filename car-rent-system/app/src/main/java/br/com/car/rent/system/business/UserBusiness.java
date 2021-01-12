
package br.com.car.rent.system.business;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.car.rent.system.dto.UserDto;
import br.com.car.rent.system.entity.Customer;
import br.com.car.rent.system.exception.CheckUserException;
import br.com.car.rent.system.exception.VerifyUserException;
import br.com.car.rent.system.filter.UserFilter;
import br.com.car.rent.system.repository.CustomerRepository;
import br.com.car.rent.system.utils.CpfCnpjValidator;

@Component
public class UserBusiness {

	@Autowired
	private CustomerRepository userRepository;

	@Autowired
	private CpfCnpjValidator cpfCnpjValidator;

	public Customer saveUser(final UserDto userDto) {

		if (!cpfCnpjValidator.isValid(userDto.getCpfCnpj())) {
			throw new CheckUserException();
		}

		final Optional<Customer> usuarioOptional = userRepository.findByCpfCnpj(removeCaracter(userDto.getCpfCnpj()));
		if (usuarioOptional.isPresent()) {
			throw new VerifyUserException();
		}

		final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		final String password = encoder.encode(userDto.getPassword());

		final Customer user = new Customer(null,
				userDto.getName(),
				removeCaracter(userDto.getCpfCnpj()),
				userDto.getMail(),
				password);
		return persistDataUser(user);
	}

	private Customer persistDataUser(final Customer user) {
		return userRepository.save(user);
	}

	public List<UserDto> searchAllUser() {
		final List<Customer> cars = userRepository.findAll();
		return converterUserDto(cars);
	}

	@Transactional
	public Page<UserDto> searchUserFilter(final UserFilter userFilter, final Pageable pageable) {
		final Page<Customer> pageCustomers = userRepository.searchCustomerFilter(userFilter, pageable);

		final List<UserDto> userDtos = converterUserDto(pageCustomers.getContent());

		final Page<UserDto> pageUserDtos = new PageImpl<>(userDtos, pageable, pageCustomers.getTotalElements());

		return pageUserDtos;
	}

	private List<UserDto> converterUserDto(final List<Customer> users) {
		return users.parallelStream().map(mapper -> userConverterUserDto(mapper)).collect(Collectors.toList());
	}

	private UserDto userConverterUserDto(final Customer user) {
		return new UserDto(user.getName(), user.getCpfCnpj(), user.getMail(), user.getPassword());
	}

	@Transactional
	public void removeUser(final String cpfCnpj) {

		if (!cpfCnpjValidator.isValid(cpfCnpj)) {
			throw new CheckUserException();
		}

		final Optional<Customer> usuarioOptional = userRepository.findByCpfCnpj(removeCaracter(cpfCnpj));
		final Customer customer =
				usuarioOptional.orElseThrow(() -> new UsernameNotFoundException("CPF/CNPJ do usuário incorreto"));

		userRepository.delete(customer);
	}

	private String removeCaracter(final String cpfCnpj) {
		return cpfCnpj.replace(".", "").replace("-", "").replace("/", "");
	}

	@Transactional
	public UserDto editUser(final String cpfCnpj, @Valid final UserDto userDto) {

		if (!cpfCnpjValidator.isValid(cpfCnpj) || !cpfCnpjValidator.isValid(userDto.getCpfCnpj())) {
			throw new CheckUserException();
		}

		final Optional<Customer> usuarioOptional = userRepository.findByCpfCnpj(removeCaracter(cpfCnpj));
		final Customer customer =
				usuarioOptional.orElseThrow(() -> new UsernameNotFoundException("CPF/CNPJ do usuário incorreto"));

		final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		final String password = encoder.encode(userDto.getPassword());

		final Customer customerResponse = persistDataUser(new Customer(customer
				.getUserId(), userDto.getName(), removeCaracter(userDto.getCpfCnpj()), userDto.getMail(), password));

		return userConverterUserDto(customerResponse);
	}
}
