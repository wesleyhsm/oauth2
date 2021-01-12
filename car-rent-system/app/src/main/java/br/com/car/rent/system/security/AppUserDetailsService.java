
package br.com.car.rent.system.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.car.rent.system.entity.Customer;
import br.com.car.rent.system.repository.CustomerRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private CustomerRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
		final Optional<Customer> usuarioOptional = userRepository.findByMail(email);
		final Customer customer =
				usuarioOptional.orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou senha incorretos"));
		return new User(email, customer.getPassword(), getPermissoes(customer));
	}

	private Collection<? extends GrantedAuthority> getPermissoes(final Customer customer) {
		final Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		return authorities;
	}
}
