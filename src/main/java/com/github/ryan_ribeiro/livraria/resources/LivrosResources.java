package com.github.ryan_ribeiro.livraria.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.github.ryan_ribeiro.livraria.domain.Comentario;
import com.github.ryan_ribeiro.livraria.domain.Livro;
import com.github.ryan_ribeiro.livraria.services.LivrosService;
import com.github.ryan_ribeiro.livraria.services.exceptions.EditoraNaoEncontradaException;

@RestController
@RequestMapping("/livros")
public class LivrosResources {
	
	@Autowired
	private LivrosService livroService;
	
//	@GetMapping(value = "/livros")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Livro>> listar() {
		return ResponseEntity.status(HttpStatus.OK).body(livroService.listar());
	}
	
	
//	@PostMapping("livros")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@RequestBody Livro livro) {
		livro = livroService.salvar(livro);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(livro.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Livro> buscar(@PathVariable("id") Long id) {
		Livro livro = livroService.buscar(id);
		return (ResponseEntity<Livro>) ResponseEntity.status(HttpStatus.OK).body(livro);
	}
	
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
		livroService.deletar(id);
		return ResponseEntity.noContent().build();
	}

	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@RequestBody Livro livro, 
			@PathVariable ("id") Long id) {
		livro.setId(id);
		livroService.atualizar(livro);
		
		return ResponseEntity.noContent().build();
	}
	
	
	@GetMapping(value = "consultarEditoraJPQL")
	public ResponseEntity<List<Livro>> consultarEditoraJPQL(@RequestParam("editora") String editora) {
		List<Livro> livro = null;
		try {
			livro = livroService.consultarEditoraJPQL(editora);
		} catch(EditoraNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(livro);
	}

	
	@GetMapping(value = "consultarEditora")
	public ResponseEntity<List<Livro>> consultarEditora(@RequestParam("editora") String editora) {
		List<Livro> livro = null;
		try {
			livro = livroService.consultarEditora(editora);
		} catch(EditoraNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(livro);
	}
	
	
	@RequestMapping(value = "/{id}/comentarios", method	= RequestMethod.POST)
	public ResponseEntity<Void> adicionarComentario(@PathVariable("id") Long livroId, 
									@RequestBody Comentario comentario) {
		livroService.salvarComentario(livroId, comentario);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		
		return ResponseEntity.created(uri).build();
	}
}
