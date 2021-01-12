
package br.com.car.rent.system.exception;

public class CheckBoardCarException extends RuntimeException {

	private static final long serialVersionUID = -5729796807587882639L;

	public CheckBoardCarException() {
		super(String.format("Erro placa do carro inválida!"));
	}
}
