package com.patrickcarmo.meucarrinho.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.patrickcarmo.meucarrinho.domain.Cliente;
import com.patrickcarmo.meucarrinho.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResources {
	
	@Autowired
	private ClienteService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?> listar() {
		List<Cliente> obj = service.buscarTodos();
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id){
		Cliente obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
	}
	
}