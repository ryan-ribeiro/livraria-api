package com.github.ryan_ribeiro.livraria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.ryan_ribeiro.livraria.domain.Autor;

public interface AutoresRepository extends JpaRepository<Autor, Long>{

}
