package com.patrickcarmo.meucarrinho.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.patrickcarmo.meucarrinho.domain.Categoria;
import com.patrickcarmo.meucarrinho.repositories.CategoriaRepository;
import com.patrickcarmo.meucarrinho.services.exceptions.DataIntegrityException;
import com.patrickcarmo.meucarrinho.services.exceptions.ObjectNotFoundException;


@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		Categoria obj = repo.findOne(id);
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
					+ ", Tipo: " + Categoria.class.getName());
		}
		return obj;
	}
	
	public List<Categoria> findAll() {
		return repo.findAll();		
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produtos.");
		}
	}
	
}
