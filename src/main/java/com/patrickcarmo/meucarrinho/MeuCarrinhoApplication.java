package com.patrickcarmo.meucarrinho;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.patrickcarmo.meucarrinho.services.S3Service;

@SpringBootApplication
public class MeuCarrinhoApplication implements CommandLineRunner{
	
	@Autowired
	private S3Service s3Service;
	
	public static void main(String[] args) {
		SpringApplication.run(MeuCarrinhoApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {	
		s3Service.uploadFile("C:\\temp\\fotos\\Gg.png");
	}
	
}
