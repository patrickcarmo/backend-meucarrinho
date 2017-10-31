package com.patrickcarmo.meucarrinho.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.patrickcarmo.meucarrinho.domain.Categoria;
import com.patrickcarmo.meucarrinho.repositories.CategoriaRepository;
import com.patrickcarmo.meucarrinho.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResources {
	
	@Autowired
	private CategoriaService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?> listar() {
		List<Categoria> obj = service.buscarTodos();
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id){
		Categoria obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
	}
	
}
