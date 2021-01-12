
package br.com.car.rent.system.exception;

public class CheckUserException extends RuntimeException {

	private static final long serialVersionUID = -5729796807587882639L;

	public CheckUserException() {
		super(String.format("Erro CPF ou CNPJ inválido!"));
	}
}
