package com.github.ryan_ribeiro.livraria.services.exceptions;

public class EditoraNaoEncontradaException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8159215083161534538L;

	public EditoraNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public EditoraNaoEncontradaException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
