package com.github.ryan_ribeiro.livraria.handler;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.github.ryan_ribeiro.livraria.services.exceptions.AutorExistenteException;
import com.github.ryan_ribeiro.livraria.services.exceptions.AutorNaoEncontradoException;
import com.github.ryan_ribeiro.livraria.services.exceptions.LivroNaoEncontradoException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<ApiException> handleNoResourceFoundException(NoResourceFoundException e,
			HttpServletRequest request) {
		ApiException erro = new ApiException();
		erro.setStatus(HttpStatus.NOT_FOUND);
		erro.setStatusCode(Long.valueOf(HttpStatus.NOT_FOUND.value()));
		erro.setTitulo(e.getMessage());
		erro.setTimeStamp(System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}

	@ExceptionHandler(LivroNaoEncontradoException.class)
	public ResponseEntity<ApiException> handleLivroNaoEncontradoException(LivroNaoEncontradoException e,
			HttpServletRequest request) {
		ApiException erro = new ApiException();
		erro.setStatus(HttpStatus.NOT_FOUND);
		erro.setStatusCode(Long.valueOf(HttpStatus.NOT_FOUND.value()));
		erro.setTitulo("O livro não pôde ser encontrado");
		erro.setMensagemDesenvolvedor("http://erros.socialbooks.com/404");
		erro.setTimeStamp(System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(AutorExistenteException.class)
	public ResponseEntity<ApiException> handleAutorExistenteException(AutorExistenteException e,
			HttpServletRequest request) {
		ApiException erro = new ApiException();
		erro.setStatus(HttpStatus.CONFLICT);
		erro.setStatusCode(Long.valueOf(HttpStatus.CONFLICT.value()));
		erro.setMensagemDesenvolvedor("http://erros.socialbooks.com/404");
		erro.setTitulo(e.getMessage());
		erro.setTimeStamp(System.currentTimeMillis());

		return ResponseEntity.status(erro.getStatus()).body(erro);
	}
	
	@ExceptionHandler(AutorNaoEncontradoException.class)
	public ResponseEntity<ApiException> handleAutorNaoEncontradoException(AutorNaoEncontradoException e,
			HttpServletRequest request) {
		ApiException erro = new ApiException();
		erro.setStatus(HttpStatus.NOT_FOUND);
		erro.setStatusCode(Long.valueOf(HttpStatus.NOT_FOUND.value()));
		erro.setTitulo(e.getMessage());
		erro.setTimeStamp(System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ApiException> handleDataIntegrityViolationException(DataIntegrityViolationException e,
			HttpServletRequest request) {
		ApiException erro = new ApiException();
		erro.setStatus(HttpStatus.BAD_REQUEST);
		erro.setStatusCode(Long.valueOf(HttpStatus.BAD_REQUEST.value()));
		erro.setTitulo(e.getMessage());
		erro.setTimeStamp(System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
}
