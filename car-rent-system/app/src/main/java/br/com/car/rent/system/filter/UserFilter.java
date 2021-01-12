
package br.com.car.rent.system.filter;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserFilter implements Serializable {

	private static final long serialVersionUID = 5003230287267219027L;

	private String name;
	private String cpfCnpj;
	private String mail;

	@JsonCreator
	public static UserFilter create(final @JsonProperty("name") String name,
			final @JsonProperty("cpfCnpj") String cpfCnpj,
			final @JsonProperty("mail") String mail) {
		return new UserFilter(name, cpfCnpj, mail);
	}

	public UserFilter() {

	}

	public UserFilter(final String name, final String cpfCnpj, final String mail) {
		super();
		this.name = name;
		this.cpfCnpj = cpfCnpj;
		this.mail = mail;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((cpfCnpj == null) ? 0 : cpfCnpj.hashCode());
		result = (prime * result) + ((mail == null) ? 0 : mail.hashCode());
		result = (prime * result) + ((name == null) ? 0 : name.hashCode());
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
		final UserFilter other = (UserFilter) obj;
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
		return true;
	}

	@Override
	public String toString() {
		return "UserDto [name=" + name + ", cpfCnpj=" + cpfCnpj + ", mail=" + mail + "]";
	}
}
