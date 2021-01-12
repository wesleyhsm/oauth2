
package br.com.car.rent.system.dto;

import java.io.Serializable;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDto implements Serializable {

	private static final long serialVersionUID = -8151996781931162819L;

	@NotBlank(message = "O nome é obrigatório")
	@Size(min = 3, max = 50, message = "O tamanho do nome é inválido")
	private String name;

	@NotBlank(message = "O CPF/CNPJ é obrigatório")
	@Size(min = 11, max = 18, message = "O tamanho do CPF/CNPJ é inválido")
	private String cpfCnpj;

	@NotBlank(message = "O e-mail é obrigatório")
	@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",
			message = "\"O e-mail é inválido\"")
	@Size(min = 5, max = 100, message = "O tamanho do e-mail é inválido")
	private String mail;

	@NotBlank(message = "A senha é obrigatório")
	@Size(min = 6, max = 14, message = "O tamanho da senha deve ser entre 6 e 14 caracteres")
	private String password;

	@JsonCreator
	public static UserDto create(final @JsonProperty("name") String name,
			final @JsonProperty("cpfCnpj") String cpfCnpj,
			final @JsonProperty("mail") String mail,
			final @JsonProperty("password") String password) {
		return new UserDto(name, cpfCnpj, mail, password);
	}

	public UserDto() {

	}

	public UserDto(final String name, final String cpfCnpj, final String mail, final String password) {
		super();
		this.name = name;
		this.cpfCnpj = cpfCnpj;
		this.mail = mail;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(final String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(final String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((cpfCnpj == null) ? 0 : cpfCnpj.hashCode());
		result = (prime * result) + ((mail == null) ? 0 : mail.hashCode());
		result = (prime * result) + ((name == null) ? 0 : name.hashCode());
		result = (prime * result) + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final UserDto other = (UserDto) obj;
		if (cpfCnpj == null) {
			if (other.cpfCnpj != null) {
				return false;
			}
		} else if (!cpfCnpj.equals(other.cpfCnpj)) {
			return false;
		}
		if (mail == null) {
			if (other.mail != null) {
				return false;
			}
		} else if (!mail.equals(other.mail)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (password == null) {
			if (other.password != null) {
				return false;
			}
		} else if (!password.equals(other.password)) {
			return false;
		}
		return true;
	}
}
