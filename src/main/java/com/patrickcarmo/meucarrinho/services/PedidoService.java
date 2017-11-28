package com.patrickcarmo.meucarrinho.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patrickcarmo.meucarrinho.domain.Pedido;
import com.patrickcarmo.meucarrinho.repositories.PedidoRepository;
import com.patrickcarmo.meucarrinho.services.exceptions.DataIntegrityException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	public Pedido find(Integer id) {
		Pedido obj = repo.findOne(id);
		if (obj == null) {
			throw new DataIntegrityException("Objeto não encontrado! Id: " + id
					+ ", Tipo: " + Pedido.class.getName());
		}
		return obj;
	}
	
	public List<Pedido> findAll() {
		List<Pedido> obj = repo.findAll();
		return obj;
	}

}
