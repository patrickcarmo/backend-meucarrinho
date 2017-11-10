package com.patrickcarmo.meucarrinho;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.patrickcarmo.meucarrinho.domain.Categoria;
import com.patrickcarmo.meucarrinho.domain.Cidade;
import com.patrickcarmo.meucarrinho.domain.Cliente;
import com.patrickcarmo.meucarrinho.domain.Endereco;
import com.patrickcarmo.meucarrinho.domain.Estado;
import com.patrickcarmo.meucarrinho.domain.ItemPedido;
import com.patrickcarmo.meucarrinho.domain.Pagamento;
import com.patrickcarmo.meucarrinho.domain.PagamentoComBoleto;
import com.patrickcarmo.meucarrinho.domain.PagamentoComCartao;
import com.patrickcarmo.meucarrinho.domain.Pedido;
import com.patrickcarmo.meucarrinho.domain.Produto;
import com.patrickcarmo.meucarrinho.domain.enums.EstadoPagamento;
import com.patrickcarmo.meucarrinho.domain.enums.TipoCliente;
import com.patrickcarmo.meucarrinho.repositories.CategoriaRepository;
import com.patrickcarmo.meucarrinho.repositories.CidadeRepository;
import com.patrickcarmo.meucarrinho.repositories.ClienteRepository;
import com.patrickcarmo.meucarrinho.repositories.EnderecoRepository;
import com.patrickcarmo.meucarrinho.repositories.EstadoRepository;
import com.patrickcarmo.meucarrinho.repositories.ItemPedidoRepository;
import com.patrickcarmo.meucarrinho.repositories.PagamentoRepository;
import com.patrickcarmo.meucarrinho.repositories.PedidoRepository;
import com.patrickcarmo.meucarrinho.repositories.ProdutoRepository;

@SpringBootApplication
public class MeuCarrinhoApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(MeuCarrinhoApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Games");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		Produto p4 = new Produto(null, "Overwatch", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		cat3.getProdutos().addAll(Arrays.asList(p1,p4));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		p4.getCategorias().addAll(Arrays.asList(cat1, cat3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Av. Matos", "105", "Sala 900", "Centro", "382208012", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("01/11/2017 11:00"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("09/11/2017 16:00"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/11/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		categoriaRepository.save(Arrays.asList(cat1, cat2, cat3));
		produtoRepository.save(Arrays.asList(p1, p2, p3, p4));
		estadoRepository.save(Arrays.asList(est1, est2));
		cidadeRepository.save(Arrays.asList(c1, c2, c3));	
		clienteRepository.save(Arrays.asList(cli1));
		enderecoRepository.save(Arrays.asList(e1,e2));
		pedidoRepository.save(Arrays.asList(ped1,ped2));
		pagamentoRepository.save(Arrays.asList(pagto1,pagto2));
		itemPedidoRepository.save(Arrays.asList(ip1, ip2, ip3));
		
		
	}
	
	
	
}
