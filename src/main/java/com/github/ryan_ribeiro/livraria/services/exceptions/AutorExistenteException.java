package com.github.ryan_ribeiro.livraria.services.exceptions;

public class AutorExistenteException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1179590653079186310L;

	public AutorExistenteException(String mensagem) {
		super(mensagem);
	}
	
	public AutorExistenteException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
