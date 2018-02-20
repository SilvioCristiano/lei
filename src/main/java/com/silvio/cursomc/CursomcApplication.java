package com.silvio.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import javax.xml.stream.events.EndElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.silvio.cursomc.domain.Categoria;
import com.silvio.cursomc.domain.Cidade;
import com.silvio.cursomc.domain.Cliente;
import com.silvio.cursomc.domain.Endereco;
import com.silvio.cursomc.domain.Estado;
import com.silvio.cursomc.domain.Pagamento;
import com.silvio.cursomc.domain.PagamentoComBoleto;
import com.silvio.cursomc.domain.PagamentoComCartao;
import com.silvio.cursomc.domain.Pedido;
import com.silvio.cursomc.domain.Produto;
import com.silvio.cursomc.domain.enums.EstadoPagamento;
import com.silvio.cursomc.domain.enums.TipoCliente;
import com.silvio.cursomc.repositories.CategoriaRepository;
import com.silvio.cursomc.repositories.CidadeRepository;
import com.silvio.cursomc.repositories.ClienteRepository;
import com.silvio.cursomc.repositories.EnderecoRepository;
import com.silvio.cursomc.repositories.EstadoRepository;
import com.silvio.cursomc.repositories.PagamentoRepository;
import com.silvio.cursomc.repositories.PedidoRepository;
import com.silvio.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	
	@Override
	public void run(String... arg0) throws Exception {
		
		Categoria cat1 = new Categoria(null,"Ïnformatica");
		Categoria cat2 = new Categoria(null, "Äccount");
		
		Produto p1 = new Produto(null, "Computador", 2.000); 
		Produto p2 = new Produto(null, "Impressora", 1.000); 
		Produto p3 = new Produto(null, "Mouse", 6.0); 
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.save(Arrays.asList(cat1,cat2));
		produtoRepository.save(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "Sao Paulo");
		

		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "Santo Andre", est2);
		Cidade c3 = new Cidade(null, "Santos", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.save(Arrays.asList(est1,est2));
		cidadeRepository.save(Arrays.asList(c1,c2, c3));
		
		Cliente cli1 = new Cliente(null, "Silvio Cristiano da Silva","sivuca1@gmail.com", "11983059444", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("12345678", "98765432"));
						
		Endereco e1 = new Endereco(null, "Rua Miguel Yunes", "500", "Casa 36","Bem te vi", "3666600", c1, cli1);
		Endereco e2 = new Endereco(null, "Rua Joao ferreira", "531", "Casa 41","Bem te vi", "3066660", c2, cli1);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		clienteRepository.save(Arrays.asList(cli1));
		enderecoRepository.save(Arrays.asList(e1,e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("23/02/2018 10:23"),cli1, e1 );
		Pedido ped2 = new Pedido(null, sdf.parse("29/03/2018 09:43"),cli1, e2 );
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("29/03/2018 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.save(Arrays.asList(ped1,ped2));
		pagamentoRepository.save(Arrays.asList(pagto1,pagto2));
	}
}
