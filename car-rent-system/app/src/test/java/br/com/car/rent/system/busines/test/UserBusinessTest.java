
package br.com.car.rent.system.busines.test;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.car.rent.system.business.UserBusiness;
import br.com.car.rent.system.dto.UserDto;
import br.com.car.rent.system.entity.Customer;
import br.com.car.rent.system.exception.CheckUserException;
import br.com.car.rent.system.exception.VerifyUserException;
import br.com.car.rent.system.repository.CustomerRepository;
import br.com.car.rent.system.utils.CpfCnpjValidator;

@RunWith(MockitoJUnitRunner.class)
public class UserBusinessTest {

	@InjectMocks
	private UserBusiness userBusiness;

	@Mock
	private CustomerRepository userRepository;

	@Mock
	private CpfCnpjValidator cpfCnpjValidator;

	@Mock
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Test
	public void saveUserTest() throws ParseException {
		final UserDto userDto = createUserDto();
		final Customer customerCreate = createCustomer();

		Mockito.when(cpfCnpjValidator.isValid(userDto.getCpfCnpj())).thenReturn(Boolean.TRUE);
		Mockito.when(userRepository.findByCpfCnpj(userDto.getCpfCnpj())).thenReturn(Optional.ofNullable(null));
		Mockito.when(userBusiness.saveUser(userDto)).thenReturn(customerCreate);

		Assert.assertEquals(userDto.getCpfCnpj(), customerCreate.getCpfCnpj());
		Assert.assertEquals(userDto.getMail(), customerCreate.getMail());
		Assert.assertEquals(userDto.getName(), customerCreate.getName());
		Assert.assertEquals(userDto.getPassword(), customerCreate.getPassword());
	}

	@Test(expected = CheckUserException.class)
	public void saveUserTest2() throws ParseException {
		final UserDto userDto = createUserDto();
		final Customer customerCreate = createCustomer();

		Mockito.when(cpfCnpjValidator.isValid(userDto.getCpfCnpj())).thenReturn(Boolean.FALSE);
		Mockito.when(userRepository.findByCpfCnpj(userDto.getCpfCnpj())).thenReturn(Optional.ofNullable(null));
		Mockito.when(userBusiness.saveUser(userDto)).thenReturn(customerCreate);

		Assert.assertEquals(userDto.getCpfCnpj(), customerCreate.getCpfCnpj());
		Assert.assertEquals(userDto.getMail(), customerCreate.getMail());
		Assert.assertEquals(userDto.getName(), customerCreate.getName());
		Assert.assertEquals(userDto.getPassword(), customerCreate.getPassword());
	}

	@Test(expected = VerifyUserException.class)
	public void saveUserTest3() throws ParseException {
		final UserDto userDto = createUserDto();
		final Customer customerCreate = createCustomer();
		final Optional<Customer> customerCreateOptional = createCustomerOptional();

		Mockito.when(cpfCnpjValidator.isValid(userDto.getCpfCnpj())).thenReturn(Boolean.TRUE);
		Mockito.when(userRepository.findByCpfCnpj(userDto.getCpfCnpj())).thenReturn(customerCreateOptional);
		Mockito.when(userBusiness.saveUser(userDto)).thenReturn(customerCreate);

		Assert.assertEquals(userDto.getCpfCnpj(), customerCreate.getCpfCnpj());
		Assert.assertEquals(userDto.getMail(), customerCreate.getMail());
		Assert.assertEquals(userDto.getName(), customerCreate.getName());
		Assert.assertEquals(userDto.getPassword(), customerCreate.getPassword());
	}

	@Test(expected = NullPointerException.class)
	public void saveUserTest4() throws ParseException {
		final UserDto userDto = createUserDto();
		final Customer customerCreate = createCustomer();

		Mockito.when(cpfCnpjValidator.isValid(userDto.getCpfCnpj())).thenReturn(Boolean.TRUE);
		Mockito.when(userRepository.findByCpfCnpj(userDto.getCpfCnpj())).thenReturn(null);
		Mockito.when(userBusiness.saveUser(userDto)).thenReturn(customerCreate);

		Assert.assertEquals(userDto.getCpfCnpj(), customerCreate.getCpfCnpj());
		Assert.assertEquals(userDto.getMail(), customerCreate.getMail());
		Assert.assertEquals(userDto.getName(), customerCreate.getName());
		Assert.assertEquals(userDto.getPassword(), customerCreate.getPassword());
	}

	@Test
	public void searchAllUserTest() {
		final List<UserDto> userDtos = createUserDtos();
		final List<Customer> customers = createCustomerDtos();

		Mockito.when(userBusiness.searchAllUser()).thenReturn(userDtos);
		Mockito.when(userRepository.findAll()).thenReturn(customers);

		final List<Customer> customersRepository = userRepository.findAll();

		Assert.assertFalse(customers.isEmpty());
		Assert.assertFalse(userDtos.isEmpty());
		Assert.assertEquals(customersRepository.get(0).getCpfCnpj(), userDtos.get(0).getCpfCnpj());
		Assert.assertEquals(customersRepository.get(0).getMail(), userDtos.get(0).getMail());
		Assert.assertEquals(customersRepository.get(0).getName(), userDtos.get(0).getName());
		Assert.assertEquals(customersRepository.get(0).getPassword(), userDtos.get(0).getPassword());
	}

	@Test
	public void removeUserTest() {
		final String cpfCnpj = "43784802000142";
		final Optional<Customer> customerCreate = createCustomerOptional();

		Mockito.when(cpfCnpjValidator.isValid(cpfCnpj)).thenReturn(Boolean.TRUE);
		Mockito.when(userRepository.findByCpfCnpj(cpfCnpj)).thenReturn(customerCreate);

		userBusiness.removeUser(cpfCnpj);
	}

	@Test(expected = CheckUserException.class)
	public void removeUserTest2() {
		final String cpfCnpj = "43784802000109";
		final Optional<Customer> customerCreate = createCustomerOptional();

		Mockito.when(cpfCnpjValidator.isValid(cpfCnpj)).thenReturn(Boolean.FALSE);
		Mockito.when(userRepository.findByCpfCnpj(cpfCnpj)).thenReturn(customerCreate);

		userBusiness.removeUser(cpfCnpj);
	}

	@Test(expected = CheckUserException.class)
	public void editUserTest() throws UsernameNotFoundException {
		final UserDto userDto = createUserDto();
		final Optional<Customer> customerCreate = createCustomerOptional();
		final String cpfCnpj = "43784802000109";

		Mockito.when(cpfCnpjValidator.isValid(userDto.getCpfCnpj())).thenReturn(Boolean.FALSE);
		Mockito.when(userRepository.findByCpfCnpj(cpfCnpj)).thenReturn(customerCreate);

		final Customer customer = userRepository.findByCpfCnpj(cpfCnpj).get();
		Mockito.when(userRepository.save(customer)).thenReturn(customer);

		final UserDto userDtoTeste = userBusiness.editUser(cpfCnpj, userDto);

		Assert.assertEquals(customerCreate.get(), customer);
		Assert.assertEquals(userDtoTeste.getCpfCnpj(), customer.getCpfCnpj());
		Assert.assertEquals(userDtoTeste.getMail(), customer.getMail());
		Assert.assertEquals(userDtoTeste.getName(), customer.getName());
		Assert.assertEquals(userDtoTeste.getPassword(), customer.getPassword());
	}

	private UserDto createUserDto() {
		return new UserDto("teste", "43784802000142", "wesleyteste@hotmail.com", "password");
	}

	private List<UserDto> createUserDtos() {
		return Arrays.asList(createUserDto());
	}

	private Customer createCustomer() {
		return new Customer(Long.valueOf(10L), "teste", "43784802000142", "wesleyteste@hotmail.com", "password");
	}

	private List<Customer> createCustomerDtos() {
		return Arrays.asList(createCustomer());
	}

	private Optional<Customer> createCustomerOptional() {
		return Optional.of(createCustomer());
	}
}
