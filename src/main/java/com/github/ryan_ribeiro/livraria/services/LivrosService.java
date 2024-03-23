package com.github.ryan_ribeiro.livraria.services;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.ryan_ribeiro.livraria.domain.Comentario;
import com.github.ryan_ribeiro.livraria.domain.Livro;
import com.github.ryan_ribeiro.livraria.repository.ComentarioRepository;
import com.github.ryan_ribeiro.livraria.repository.LivrosRepository;
import com.github.ryan_ribeiro.livraria.services.exceptions.LivroNaoEncontradoException;

@Service
public class LivrosService {
	@Autowired
	private LivrosRepository livroRepository;
	
	@Autowired
	private ComentarioRepository comentarioRepository;
	
	public List<Livro> listar() {
		return livroRepository.findAll();
	}
	
	public Livro buscar(Long id) {
		Livro livro =  livroRepository.findById(id).orElse(null);
		
		if(livro == null) {
			throw new LivroNaoEncontradoException("O livro " + id + " não foi encontrado");
		}
		
		return livro;
	}
	
	public Livro salvar(Livro livro) {
		livro.setId(null);
		return livroRepository.save(livro);
	}
	
	public void deletar(Long id) {
//		try {
//			
//			
//		} catch(Exception e) {
//			throw new LivroNaoEncontradoException("O livro " + id + " não pôde ser encontrado");
//		}
		
		Livro livro = livroRepository.findById(id)
		.orElseThrow(() -> new LivroNaoEncontradoException("O livro " + id + " não pôde ser encontrado"));
		
		livroRepository.delete(livro);
			
	}
	
	public void atualizar(Livro livro) {
		verificarExistencia(livro);
		livroRepository.save(livro);
	}
	
	private void verificarExistencia(Livro livro) {
		buscar(livro.getId());
	}
	
	public Comentario salvarComentario(Long livroId, Comentario comentario) {
		Livro livro = buscar(livroId);
		
		comentario.setLivro(livro);
		comentario.setData(new Date (System.currentTimeMillis()));
		
		return comentarioRepository.save(comentario);
	}
	
	public List<Livro> consultarEditora(String editora) {
//		try {
		return livroRepository.consultaEditora(editora);
//		} catch() {
//			
//		}
//		if (editora == ) ?//
	}
	
	public List<Livro> consultarEditoraJPQL(String editora) {
		return livroRepository.consultaEditoraJPQL(editora);
	}
}
