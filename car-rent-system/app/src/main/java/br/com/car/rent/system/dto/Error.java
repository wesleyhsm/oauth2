
package br.com.car.rent.system.dto;

public class Error {

	private int code;
	private String message;

	private Error() {}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public static class Builder {

		private final Error error;

		public Builder() {
			error = new Error();
		}

		public Builder setCode(final int code) {
			error.code = code;
			return this;
		}

		public Builder setMessage(final String message) {
			error.message = message;
			return this;
		}

		public Error build() {
			return error;
		}

	}

}
