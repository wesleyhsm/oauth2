
package br.com.car.rent.system.filter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CarFilter implements Serializable {

	private static final long serialVersionUID = -384791887114793573L;

	private String brand;
	private String model;
	private String board;
	private BigDecimal minPrice;
	private BigDecimal maxPrice;
	private Date minYear;
	private Date maxYear;

	@JsonCreator
	public static CarFilter create(final @JsonProperty("brand") String brand,
			final @JsonProperty("model") String model,
			final @JsonProperty("board") String board,
			final @JsonProperty("minPrice") BigDecimal minPrice,
			final @JsonProperty("maxPrice") BigDecimal maxPrice,
			final @JsonProperty("minYear") Date minYear,
			final @JsonProperty("maxYear") Date maxYear) {
		return new CarFilter(brand, model, board, minPrice, maxPrice, minYear, maxYear);
	}

	public CarFilter() {

	}

	public CarFilter(final String brand,
			final String model,
			final String board,
			final BigDecimal minPrice,
			final BigDecimal maxPrice,
			final Date minYear,
			final Date maxYear) {
		super();
		this.brand = brand;
		this.model = model;
		this.board = board;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.minYear = minYear;
		this.maxYear = maxYear;
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

	public BigDecimal getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(final BigDecimal minPrice) {
		this.minPrice = minPrice;
	}

	public BigDecimal getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(final BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}

	public Date getMinYear() {
		return minYear;
	}

	public void setMinYear(final Date minYear) {
		this.minYear = minYear;
	}

	public Date getMaxYear() {
		return maxYear;
	}

	public void setMaxYear(final Date maxYear) {
		this.maxYear = maxYear;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((board == null) ? 0 : board.hashCode());
		result = (prime * result) + ((brand == null) ? 0 : brand.hashCode());
		result = (prime * result) + ((maxPrice == null) ? 0 : maxPrice.hashCode());
		result = (prime * result) + ((maxYear == null) ? 0 : maxYear.hashCode());
		result = (prime * result) + ((minPrice == null) ? 0 : minPrice.hashCode());
		result = (prime * result) + ((minYear == null) ? 0 : minYear.hashCode());
		result = (prime * result) + ((model == null) ? 0 : model.hashCode());
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
		final CarFilter other = (CarFilter) obj;
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
		if (maxPrice == null) {
			if (other.maxPrice != null) {
				return false;
			}
		} else if (!maxPrice.equals(other.maxPrice)) {
			return false;
		}
		if (maxYear == null) {
			if (other.maxYear != null) {
				return false;
			}
		} else if (!maxYear.equals(other.maxYear)) {
			return false;
		}
		if (minPrice == null) {
			if (other.minPrice != null) {
				return false;
			}
		} else if (!minPrice.equals(other.minPrice)) {
			return false;
		}
		if (minYear == null) {
			if (other.minYear != null) {
				return false;
			}
		} else if (!minYear.equals(other.minYear)) {
			return false;
		}
		if (model == null) {
			if (other.model != null) {
				return false;
			}
		} else if (!model.equals(other.model)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "CarFilter [brand="
				+ brand
				+ ", model="
				+ model
				+ ", board="
				+ board
				+ ", minPrice="
				+ minPrice
				+ ", maxPrice="
				+ maxPrice
				+ ", minYear="
				+ minYear
				+ ", maxYear="
				+ maxYear
				+ "]";
	}
}
