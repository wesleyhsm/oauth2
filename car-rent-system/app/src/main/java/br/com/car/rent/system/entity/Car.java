
package br.com.car.rent.system.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "car")
public class Car implements Serializable {

	private static final long serialVersionUID = 5820188964042574098L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "car_id")
	private Long carId;

	@Column(name = "brand")
	private String brand;

	@Column(name = "model")
	private String model;

	@Column(name = "board")
	private String board;

	@Column(name = "price")
	private BigDecimal price;

	@Temporal(TemporalType.DATE)
	private Date year;

	public Car() {

	}

	public Car(final Long carId,
			final String brand,
			final String model,
			final String board,
			final BigDecimal price,
			final Date year) {
		super();
		this.carId = carId;
		this.brand = brand;
		this.model = model;
		this.board = board;
		this.price = price;
		this.year = year;
	}

	public Long getCarId() {
		return carId;
	}

	public void setCarId(final Long carId) {
		this.carId = carId;
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
		result = (prime * result) + ((carId == null) ? 0 : carId.hashCode());
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
		final Car other = (Car) obj;
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
		if (carId == null) {
			if (other.carId != null) {
				return false;
			}
		} else if (!carId.equals(other.carId)) {
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
}
