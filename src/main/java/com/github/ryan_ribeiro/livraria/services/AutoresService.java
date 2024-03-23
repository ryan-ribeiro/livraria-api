package com.github.ryan_ribeiro.livraria.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.ryan_ribeiro.livraria.domain.Autor;
import com.github.ryan_ribeiro.livraria.repository.AutoresRepository;
import com.github.ryan_ribeiro.livraria.services.exceptions.AutorExistenteException;
import com.github.ryan_ribeiro.livraria.services.exceptions.AutorNaoEncontradoException;

@Service
public class AutoresService {
	@Autowired
	private AutoresRepository autoresRepository;
	
	public List<Autor> listar() {
		return autoresRepository.findAll();
	}
	
	public Autor salvar(Autor autor) {
		if(autor.getId() != null) {
			Autor a = autoresRepository.findById(autor.getId()).orElse(null);
			
			if(a != null) {
				throw new AutorExistenteException("O autor já existe.");
			}
		}
		return autoresRepository.save(autor);
	}
	
	public Autor buscar(Long id) {
		Autor autor = autoresRepository.findById(id).orElse(null);
		
		if (autor == null) {
			throw new AutorNaoEncontradoException("O autor nao foi encontrado.");
		}
		return autor;
	}
}
