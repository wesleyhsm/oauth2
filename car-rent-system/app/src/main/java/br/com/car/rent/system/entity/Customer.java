
package br.com.car.rent.system.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user")
public class Customer implements Serializable {

	private static final long serialVersionUID = 2893544012609424503L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;

	@NotNull
	@Size(min = 3, max = 50)
	@Column(name = "name", length = 50, nullable = false)
	private String name;

	@Column(name = "cpfCnpj", length = 25, nullable = false, unique = true)
	private String cpfCnpj;

	@NotNull
	@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
	@Size(min = 5, max = 255)
	@Column(name = "mail", length = 255, nullable = false, unique = true)
	private String mail;

	@NotNull
	@Size(min = 6, max = 64)
	@Column(name = "password", length = 100)
	private String password;

	public Customer() {

	}

	public Customer(final Long userId,
			final String name,
			final String cpfCnpj,
			final String mail,
			final String password) {
		super();
		this.userId = userId;
		this.name = name;
		this.cpfCnpj = cpfCnpj;
		this.mail = mail;
		this.password = password;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(final Long userId) {
		this.userId = userId;
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
		final Customer other = (Customer) obj;
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

	@Override
	public String toString() {
		return "Customer [userId="
				+ userId
				+ ", name="
				+ name
				+ ", cpfCnpj="
				+ cpfCnpj
				+ ", mail="
				+ mail
				+ ", password="
				+ password
				+ "]";
	}
}
