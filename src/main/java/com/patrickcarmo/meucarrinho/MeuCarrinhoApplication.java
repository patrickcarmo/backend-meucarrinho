package com.patrickcarmo.meucarrinho;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.patrickcarmo.meucarrinho.domain.Categoria;
import com.patrickcarmo.meucarrinho.repositories.CategoriaRepository;

@SpringBootApplication
public class MeucarrinhoApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(MeucarrinhoApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Jogos");
		
		categoriaRepository.save(Arrays.asList(cat1, cat2, cat3));
		
	}
	
	
	
}
