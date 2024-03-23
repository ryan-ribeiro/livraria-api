package com.github.ryan_ribeiro.livraria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.github.ryan_ribeiro.livraria.domain.Livro;

public interface LivrosRepository extends JpaRepository<Livro, Long>{

	
	List<Livro> findByEditora(String editora);
	
	@Query(value = "SELECT * FROM TB_LIVRO "
				 + " WHERE EDITORA = ?1", nativeQuery = true)
	List<Livro> consultaEditora(String editora);
	

	@Query(value = "SELECT L FROM Livro L"
			 + " WHERE L.editora = ?1")
	List<Livro> consultaEditoraJPQL(String editora);
	
}

