package com.lei.backend.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lei.backend.domain.Categoria;
import com.lei.backend.domain.Cidade;
import com.lei.backend.domain.Cliente;
import com.lei.backend.domain.Endereco;
import com.lei.backend.domain.Estado;
import com.lei.backend.domain.ItemPedido;
import com.lei.backend.domain.Pagamento;
import com.lei.backend.domain.PagamentoComBoleto;
import com.lei.backend.domain.PagamentoComCartao;
import com.lei.backend.domain.Pedido;
import com.lei.backend.domain.Advogado;
import com.lei.backend.domain.enums.EstadoPagamento;
import com.lei.backend.domain.enums.Perfil;
import com.lei.backend.domain.enums.TipoCliente;
import com.lei.backend.repositories.CategoriaRepository;
import com.lei.backend.repositories.CidadeRepository;
import com.lei.backend.repositories.ClienteRepository;
import com.lei.backend.repositories.EnderecoRepository;
import com.lei.backend.repositories.EstadoRepository;
import com.lei.backend.repositories.ItemPedidoRepository;
import com.lei.backend.repositories.PagamentoRepository;
import com.lei.backend.repositories.PedidoRepository;
import com.lei.backend.repositories.AdvogadoRepository;

@Service
public class DBService {

	@Autowired
	private BCryptPasswordEncoder pe;
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private AdvogadoRepository advogadoRepository;
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
	
	public void instantiateTestDatabase() throws ParseException {
		
		Categoria cat1 = new Categoria(null, "Direito de Família");
		Categoria cat2 = new Categoria(null, "Direito de Sucessões");
		Categoria cat3 = new Categoria(null, "Direito Imobiliário");
		Categoria cat4 = new Categoria(null, "Direito Trabalhista");
		Categoria cat5 = new Categoria(null, "Direito Tributário e Fiscal");
		Categoria cat6 = new Categoria(null, "Direito Previdenciário");
		Categoria cat7 = new Categoria(null, "Direito do Consumidor e Responsabilidade Civil");
		
		Advogado p1 = new Advogado(null, "Computador", 2000.00, "1234", "1111", "sivu@gmail.com", "71377991083");
		Advogado p2 = new Advogado(null, "Impressora", 800.00, "1234", "1111", "sivu@gmail.com", "71377991083");
		Advogado p3 = new Advogado(null, "Mouse", 80.00, "1234", "1111", "sivu@gmail.com", "71377991083");
		Advogado p4 = new Advogado(null, "Mesa de escritório", 300.00, "1234", "1111", "sivu@gmail.com", "71377991083");
		Advogado p5 = new Advogado(null, "Toalha", 50.00, "1234", "1111", "sivu@gmail.com", "71377991083");
		Advogado p6 = new Advogado(null, "Colcha", 200.00, "1234", "1111", "sivu@gmail.com", "71377991083");
		Advogado p7 = new Advogado(null, "TV true color", 1200.00, "1234", "1111", "sivu@gmail.com", "71377991083");
		Advogado p8 = new Advogado(null, "Roçadeira", 800.00, "1234", "1111", "sivu@gmail.com", "71377991083");
		Advogado p9 = new Advogado(null, "Abajour", 100.00, "1234", "1111", "sivu@gmail.com", "71377991083");
		Advogado p10 = new Advogado(null, "Pendente", 180.00, "1234", "1111", "sivu@gmail.com", "71377991083");
		Advogado p11 = new Advogado(null, "Shampoo", 90.00, "1234", "1111", "sivu@gmail.com", "71377991083");
		
		Advogado p12 = new Advogado(null, "Advogado 12", 10.00, "1234", "1111", "sivu@gmail.com", "71377991083");
		Advogado p13 = new Advogado(null, "Advogado 13", 10.00, "1234", "1111", "sivu@gmail.com", "71377991083");
		Advogado p14 = new Advogado(null, "Advogado 14", 10.00, "1234", "1111", "sivu@gmail.com", "71377991083");
		Advogado p15 = new Advogado(null, "Advogado 15", 10.00, "1234", "1111", "sivu@gmail.com", "71377991083");
		Advogado p16 = new Advogado(null, "Advogado 16", 10.00, "1234", "1111", "sivu@gmail.com", "71377991083");
		Advogado p17 = new Advogado(null, "Advogado 17", 10.00, "1234", "1111", "sivu@gmail.com", "71377991083");
		Advogado p18 = new Advogado(null, "Advogado 18", 10.00, "1234", "1111", "sivu@gmail.com", "71377991083");
		Advogado p19 = new Advogado(null, "Advogado 19", 10.00, "1234", "1111", "sivu@gmail.com", "71377991083");
		Advogado p20 = new Advogado(null, "Advogado 20", 10.00, "1234", "1111", "sivu@gmail.com", "71377991083");
		Advogado p21 = new Advogado(null, "Advogado 21", 10.00, "1234", "1111", "sivu@gmail.com", "71377991083");
		Advogado p22 = new Advogado(null, "Advogado 22", 10.00, "1234", "1111", "sivu@gmail.com", "71377991083");
		Advogado p23 = new Advogado(null, "Advogado 23", 10.00, "1234", "1111", "sivu@gmail.com", "71377991083");
		Advogado p24 = new Advogado(null, "Advogado 24", 10.00, "1234", "1111", "sivu@gmail.com", "71377991083");
		Advogado p25 = new Advogado(null, "Advogado 25", 10.00, "1234", "1111", "sivu@gmail.com", "71377991083");
		Advogado p26 = new Advogado(null, "Advogado 26", 10.00, "1234", "1111", "sivu@gmail.com", "71377991083");
		Advogado p27 = new Advogado(null, "Advogado 27", 10.00, "1234", "1111", "sivu@gmail.com", "71377991083");
		Advogado p28 = new Advogado(null, "Advogado 28", 10.00, "1234", "1111", "sivu@gmail.com", "71377991083");
		Advogado p29 = new Advogado(null, "Advogado 29", 10.00, "1234", "1111", "sivu@gmail.com", "71377991083");
		Advogado p30 = new Advogado(null, "Advogado 30", 10.00, "1234", "1111", "sivu@gmail.com", "71377991083");
		Advogado p31 = new Advogado(null, "Advogado 31", 10.00, "1234", "1111", "sivu@gmail.com", "71377991083");
		Advogado p32 = new Advogado(null, "Advogado 32", 10.00, "1234", "1111", "sivu@gmail.com", "71377991083");
		Advogado p33 = new Advogado(null, "Advogado 33", 10.00, "1234", "1111", "sivu@gmail.com", "71377991083");
		Advogado p34 = new Advogado(null, "Advogado 34", 10.00, "1234", "1111", "sivu@gmail.com", "71377991083");
		Advogado p35 = new Advogado(null, "Advogado 35", 10.00, "1234", "1111", "sivu@gmail.com", "71377991083");
		Advogado p36 = new Advogado(null, "Advogado 36", 10.00, "1234", "1111", "sivu@gmail.com", "71377991083");
		Advogado p37 = new Advogado(null, "Advogado 37", 10.00, "1234", "1111", "sivu@gmail.com", "71377991083");
		Advogado p38 = new Advogado(null, "Advogado 38", 10.00, "1234", "1111", "sivu@gmail.com", "71377991083");
		Advogado p39 = new Advogado(null, "Advogado 39", 10.00, "1234", "1111", "sivu@gmail.com", "71377991083");
		Advogado p40 = new Advogado(null, "Advogado 40", 10.00, "1234", "1111", "sivu@gmail.com", "71377991083");
		Advogado p41 = new Advogado(null, "Advogado 41", 10.00, "1234", "1111", "sivu@gmail.com", "71377991083");
		Advogado p42 = new Advogado(null, "Advogado 42", 10.00, "1234", "1111", "sivu@gmail.com", "71377991083");
		Advogado p43 = new Advogado(null, "Advogado 43", 10.00, "1234", "1111", "sivu@gmail.com", "71377991083");
		Advogado p44 = new Advogado(null, "Advogado 44", 10.00, "1234", "1111", "sivu@gmail.com", "71377991083");
		Advogado p45 = new Advogado(null, "Advogado 45", 10.00, "1234", "1111", "sivu@gmail.com", "71377991083");
		Advogado p46 = new Advogado(null, "Advogado 46", 10.00, "1234", "1111", "sivu@gmail.com", "71377991083");
		Advogado p47 = new Advogado(null, "Advogado 47", 10.00, "1234", "1111", "sivu@gmail.com", "71377991083");
		Advogado p48 = new Advogado(null, "Advogado 48", 10.00, "1234", "1111", "sivu@gmail.com", "71377991083");
		Advogado p49 = new Advogado(null, "Advogado 49", 10.00, "1234", "1111", "sivu@gmail.com", "71377991083");
		Advogado p50 = new Advogado(null, "Advogado 50", 10.00, "1234", "1111", "sivu@gmail.com", "71377991083");
		
		cat1.getAdvogados().addAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20,
		p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38,
		p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));
		
		p12.getCategorias().add(cat1);
		p13.getCategorias().add(cat1);
		p14.getCategorias().add(cat1);
		p15.getCategorias().add(cat1);
		p16.getCategorias().add(cat1);
		p17.getCategorias().add(cat1);
		p18.getCategorias().add(cat1);
		p19.getCategorias().add(cat1);
		p20.getCategorias().add(cat1);
		p21.getCategorias().add(cat1);
		p22.getCategorias().add(cat1);
		p23.getCategorias().add(cat1);
		p24.getCategorias().add(cat1);
		p25.getCategorias().add(cat1);
		p26.getCategorias().add(cat1);
		p27.getCategorias().add(cat1);
		p28.getCategorias().add(cat1);
		p29.getCategorias().add(cat1);
		p30.getCategorias().add(cat1);
		p31.getCategorias().add(cat1);
		p32.getCategorias().add(cat1);
		p33.getCategorias().add(cat1);
		p34.getCategorias().add(cat1);
		p35.getCategorias().add(cat1);
		p36.getCategorias().add(cat1);
		p37.getCategorias().add(cat1);
		p38.getCategorias().add(cat1);
		p39.getCategorias().add(cat1);
		p40.getCategorias().add(cat1);
		p41.getCategorias().add(cat1);
		p42.getCategorias().add(cat1);
		p43.getCategorias().add(cat1);
		p44.getCategorias().add(cat1);
		p45.getCategorias().add(cat1);
		p46.getCategorias().add(cat1);
		p47.getCategorias().add(cat1);
		p48.getCategorias().add(cat1);
		p49.getCategorias().add(cat1);
		p50.getCategorias().add(cat1);		
		
		cat1.getAdvogados().addAll(Arrays.asList(p1, p2, p3));
		cat2.getAdvogados().addAll(Arrays.asList(p2, p4));
		cat3.getAdvogados().addAll(Arrays.asList(p5, p6));
		cat4.getAdvogados().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getAdvogados().addAll(Arrays.asList(p8));
		cat6.getAdvogados().addAll(Arrays.asList(p9, p10));
		cat7.getAdvogados().addAll(Arrays.asList(p11));	
	
		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));
				
		categoriaRepository.save(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		advogadoRepository.save(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
		
		advogadoRepository.save(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20,
				p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38,
				p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.save(Arrays.asList(est1, est2));
		cidadeRepository.save(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Silvio Cristiano da Silva", "nelio.cursos@gmail.com", "36378912377", TipoCliente.PESSOAFISICA, pe.encode("123"));
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		Cliente cli2 = new Cliente(null, "Ana Costa", "nelio.iftm@gmail.com", "31628382740", TipoCliente.PESSOAFISICA, pe.encode("123"));
		cli2.getTelefones().addAll(Arrays.asList("93883321", "34252625"));
		cli2.addPerfil(Perfil.ADMIN);
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", c1, cli1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", c2, cli1);
		Endereco e3 = new Endereco(null, "Avenida Floriano", "2106", null, "Centro", "281777012", c2, cli2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		cli2.getEnderecos().addAll(Arrays.asList(e3));
		
		clienteRepository.save(Arrays.asList(cli1, cli2));
		enderecoRepository.save(Arrays.asList(e1, e2, e3));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.save(Arrays.asList(ped1, ped2));
		pagamentoRepository.save(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.save(Arrays.asList(ip1, ip2, ip3));		
	}
}