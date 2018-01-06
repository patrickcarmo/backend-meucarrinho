package com.patrickcarmo.meucarrinho.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patrickcarmo.meucarrinho.domain.ItemPedido;
import com.patrickcarmo.meucarrinho.domain.PagamentoComBoleto;
import com.patrickcarmo.meucarrinho.domain.Pedido;
import com.patrickcarmo.meucarrinho.domain.enums.EstadoPagamento;
import com.patrickcarmo.meucarrinho.repositories.ItemPedidoRepository;
import com.patrickcarmo.meucarrinho.repositories.PagamentoRepository;
import com.patrickcarmo.meucarrinho.repositories.PedidoRepository;
import com.patrickcarmo.meucarrinho.repositories.ProdutoRepository;
import com.patrickcarmo.meucarrinho.services.exceptions.ObjectNotFoundException;


@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;

	@Autowired
	private BoletoService boletoService;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public Pedido find(Integer id) {
		Pedido obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
					+ ", Tipo: " + Pedido.class.getName());
		}
		return obj;
	}

	public List<Pedido> findAll() {
		List<Pedido> obj = repo.findAll();
		return obj;
	}

	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(produtoRepository.findOne(ip.getProduto().getId()).getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.save(obj.getItens());
		return obj;
	}

}
