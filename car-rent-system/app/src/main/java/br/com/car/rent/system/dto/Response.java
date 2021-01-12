
package br.com.car.rent.system.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Response<T> {

	private boolean success;
	private T content;
	private List<Error> errors;

	public Response() {
		success = true;
	}

	public Response(final T content) {
		this();
		this.content = content;
	}

	public Response(final Error error) {
		success = false;
		errors = new ArrayList<>(1);
		errors.add(error);
	}

	public Response(final List<Error> errors) {
		success = false;
		this.errors = errors;
	}

	public boolean isSuccess() {
		return success;
	}

	public T getContent() {
		return content;
	}

	public List<Error> getErrors() {
		return errors;
	}

}
