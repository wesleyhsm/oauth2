
package br.com.car.rent.system.exception;

public class VerifyCarException extends RuntimeException {

	private static final long serialVersionUID = -5729796807587882639L;

	public VerifyCarException() {
		super(String.format("Erro carro já possui cadastro!"));
	}
}
