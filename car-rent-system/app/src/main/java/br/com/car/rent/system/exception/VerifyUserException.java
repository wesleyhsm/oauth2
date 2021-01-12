
package br.com.car.rent.system.exception;

public class VerifyUserException extends RuntimeException {

	private static final long serialVersionUID = -5729796807587882639L;

	public VerifyUserException() {
		super(String.format("Erro usuário já possui cadastro!"));
	}
}
