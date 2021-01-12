
package br.com.car.rent.system.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CarDto implements Serializable {

	private static final long serialVersionUID = 3098954032404062338L;

	@NotBlank(message = "A marca do carro é obrigatório.")
	private String brand;

	@NotBlank(message = "O modelo do carro é obrigatório.")
	private String model;

	@NotBlank(message = "A placa do é obrigatório.")
	@Size(min = 8, max = 8, message = "O tamanho da placa é inválido")
	private String board;

	@NotNull(message = "O preço do carro é obrigatório.")
	@DecimalMax(value = "99999999999999999.99", message = "{O valor máximo do carro 99999999999999999.99}")
	@DecimalMin(value = "00.01", message = "{O valor minimo do carro 00.01}")
	private BigDecimal price;

	@NotNull(message = "A data de fabricação é obrigatório.")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date year;

	@JsonCreator
	public static CarDto create(final @JsonProperty("brand") String brand,
			final @JsonProperty("model") String model,
			final @JsonProperty("board") String board,
			final @JsonProperty("price") BigDecimal price,
			final @JsonProperty("year") Date year) {
		return new CarDto(brand, model, board, price, year);
	}

	public CarDto() {

	}

	public CarDto(final String brand, final String model, final String board, final BigDecimal price, final Date year) {
		super();
		this.brand = brand;
		this.model = model;
		this.board = board;
		this.price = price;
		this.year = year;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(final String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(final String model) {
		this.model = model;
	}

	public String getBoard() {
		return board;
	}

	public void setBoard(final String board) {
		this.board = board;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(final BigDecimal price) {
		this.price = price;
	}

	public Date getYear() {
		return year;
	}

	public void setYear(final Date year) {
		this.year = year;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((board == null) ? 0 : board.hashCode());
		result = (prime * result) + ((brand == null) ? 0 : brand.hashCode());
		result = (prime * result) + ((model == null) ? 0 : model.hashCode());
		result = (prime * result) + ((price == null) ? 0 : price.hashCode());
		result = (prime * result) + ((year == null) ? 0 : year.hashCode());
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
		final CarDto other = (CarDto) obj;
		if (board == null) {
			if (other.board != null) {
				return false;
			}
		} else if (!board.equals(other.board)) {
			return false;
		}
		if (brand == null) {
			if (other.brand != null) {
				return false;
			}
		} else if (!brand.equals(other.brand)) {
			return false;
		}
		if (model == null) {
			if (other.model != null) {
				return false;
			}
		} else if (!model.equals(other.model)) {
			return false;
		}
		if (price == null) {
			if (other.price != null) {
				return false;
			}
		} else if (!price.equals(other.price)) {
			return false;
		}
		if (year == null) {
			if (other.year != null) {
				return false;
			}
		} else if (!year.equals(other.year)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "CarDto [brand="
				+ brand
				+ ", model="
				+ model
				+ ", board="
				+ board
				+ ", price="
				+ price
				+ ", year="
				+ year
				+ "]";
	}
}
