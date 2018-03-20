package com.silvio.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.silvio.cursomc.domain.Categoria;
import com.silvio.cursomc.domain.Cidade;
import com.silvio.cursomc.domain.Cliente;
import com.silvio.cursomc.domain.Endereco;
import com.silvio.cursomc.domain.Estado;
import com.silvio.cursomc.domain.ItemPedido;
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
import com.silvio.cursomc.repositories.ItemPedidoRepository;
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
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	
	@Override
	public void run(String... arg0) throws Exception {
		
		Categoria cat1 = new Categoria(null,"Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		Categoria cat3 = new Categoria(null, "Cama mesa e banho");
		Categoria cat4 = new Categoria(null, "Eletronicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoracao");
		Categoria cat7 = new Categoria(null, "Perfumaria");
		
		Produto p1 = new Produto(null, "Computador", 2.000); 
		Produto p2 = new Produto(null, "Impressora", 1.000); 
		Produto p3 = new Produto(null, "Mouse", 6.0);
		Produto p4 = new Produto(null, "Mesa de escritorio", 300.00);
		Produto p5 = new Produto(null, "Toalha", 7.0);
		Produto p6 = new Produto(null, "Colcha", 65.0);
		Produto p7 = new Produto(null, "TV true color", 890.0);
		Produto p8 = new Produto(null, "Rocadeira", 456.0);
		Produto p9 = new Produto(null, "Abajour", 34.0);
		Produto p10 = new Produto(null, "Pendente", 180.0);
		Produto p11 = new Produto(null, "Shampoo", 687.0);
	 
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));
		
		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1,cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));
		
		categoriaRepository.save(Arrays.asList(cat1,cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.save(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11));
		
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
		
		ItemPedido ip1 = new ItemPedido(ped1,p1,0.00, 1,2000.00);
		ItemPedido ip2 = new ItemPedido(ped1,p3,0.00, 2,80.00);
		ItemPedido ip3 = new ItemPedido(ped2,p2,100.00, 1,800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.save(Arrays.asList(ip1,ip2,ip3));
		
	}
}
