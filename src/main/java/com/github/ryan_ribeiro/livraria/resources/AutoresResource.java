package com.github.ryan_ribeiro.livraria.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.github.ryan_ribeiro.livraria.domain.Autor;
import com.github.ryan_ribeiro.livraria.services.AutoresService;


@RestController
@RequestMapping("/autores")
public class AutoresResource {
	@Autowired
	private AutoresService autoresService;
	
	@GetMapping(
			produces = {MediaType.APPLICATION_JSON_VALUE, 
						MediaType.APPLICATION_ATOM_XML_VALUE,
						MediaType.TEXT_PLAIN_VALUE
					   }
			)
	public ResponseEntity<List<Autor>> listar() {
		
		List<Autor> autores = autoresService.listar();
		
		return ResponseEntity
				.status(HttpStatus.OK)
//				.header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE)
				.body(autores);
	}
	
	@PostMapping
	public ResponseEntity<Void> salvar(@RequestBody Autor autor) {
		autor = autoresService.salvar(autor);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(autor.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Autor> buscar(@PathVariable("id") Long id) {
		Autor autor = autoresService.buscar(id);
		return ResponseEntity.ok(autor);
	}
}
