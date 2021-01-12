
package br.com.car.rent.system.exception;

public class CheckYearCarException extends RuntimeException {

	private static final long serialVersionUID = -5729796807587882639L;

	public CheckYearCarException() {
		super(String.format("Erro carro deve ter no maximo três anos de uso!"));
	}
}
