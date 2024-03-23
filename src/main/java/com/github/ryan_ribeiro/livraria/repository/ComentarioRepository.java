package com.github.ryan_ribeiro.livraria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.ryan_ribeiro.livraria.domain.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long >{

}
