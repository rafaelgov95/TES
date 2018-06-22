package br.com.analise.compras;

import br.com.analise.compras.Entity.*;
import br.com.analise.compras.Entity.enumeration.EstadoPagamentoEnum;
import br.com.analise.compras.Entity.enumeration.TipoClienteEnum;
import br.com.analise.compras.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class ComprasApplication implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;
    public static void main(String[] args) {
        SpringApplication.run(ComprasApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        Categoria cat1 = new Categoria(null, "informática");
        Categoria cat2 = new Categoria(null, "Escritório");

        Produto p1 = new Produto(null, "Computador", 2000.00);
        Produto p2 = new Produto(null, "Impressora", 800.00);
        Produto p3 = new Produto(null, "Mouse", 80.00);

        cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProdutos().addAll(Arrays.asList(p2));

        p1.getCategorias().addAll(Arrays.asList(cat1));
        p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
        p3.getCategorias().addAll(Arrays.asList(cat1));

        categoriaRepository.save(Arrays.asList(cat1, cat2));
        produtoRepository.save(Arrays.asList(p1, p2, p3));

        Estado est1 = new Estado(null, "Minas Gerais");
        Estado est2 = new Estado(null, "São Paulo");

        Cidade c1 = new Cidade(null, "Uberlândia", est1);
        Cidade c2 = new Cidade(null, "São Paulo", est2);
        Cidade c3 = new Cidade(null, "Campinas", est2);

        est1.getCidades().addAll(Arrays.asList(c1));
        est2.getCidades().addAll(Arrays.asList(c2, c3));

        c1.setEstado(est1);
        c2.setEstado(est2);
        c3.setEstado(est2);

        estadoRepository.save(Arrays.asList(est1, est2));
        cidadeRepository.save(Arrays.asList(c1, c2, c3));

        Cliente clil = new Cliente(null,"Maria Verde","maria.verde@verde.com","04645601173",TipoClienteEnum.PESSOAFISICA,null);
        clil.getTelefones().addAll(Arrays.asList("6732910202","6732913114"));
        Endereco end1 = new Endereco(null,"Rua das Flores","420","Ao lado das flores","Flores","79400000",clil,c1);
        Endereco end2 = new Endereco(null,"Rua Oliva","410","Publisom","Juao","803012301",clil,c2);

        clienteRepository.save(clil);
        enderecoRepository.save(Arrays.asList(end1,end2));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy hh:mm");

        Pedido pe1 = new Pedido(null,sdf.parse("30/08/2017 10:32"),end1,clil);
        Pedido pe2 = new Pedido(null,sdf.parse("30/08/2017 10:32"),end1,clil);
        Pedido pe3 = new Pedido(null,sdf.parse("30/08/2017 10:32"),end1,clil);

        PagamentoBoleto pg1 = new PagamentoBoleto(null,EstadoPagamentoEnum.QUITADO,pe1,sdf.parse("30/08/2017 10:32"));
        PagamentoComCartao pg2 = new PagamentoComCartao(null,EstadoPagamentoEnum.CANCELADO,pe2,3);
        PagamentoComCartao pg3 = new PagamentoComCartao(null,EstadoPagamentoEnum.PENDENTE,pe3,4);

        clil.getPedidos().addAll(Arrays.asList(pe1,pe2,pe3));

        pedidoRepository.save(pe1);
        pedidoRepository.save(pe2);
        pedidoRepository.save(pe3);
        clienteRepository.save(clil);

        ItemPedido item1 = new  ItemPedido(p1,pe1,12.0,10,30.0);

        ItemPedido item2 = new   ItemPedido(p1,pe1,11.0,10,30.0);

        ItemPedido item3 = new   ItemPedido(p1,pe1,13.0,10,30.0);

        pe1.getItens().addAll(Arrays.asList(item1,item2));
        pe2.getItens().addAll(Arrays.asList(item3));

        p1.getItens().addAll(Arrays.asList(item1));
        p1.getItens().addAll(Arrays.asList(item2));
        p1.getItens().addAll(Arrays.asList(item3));

        itemPedidoRepository.save(Arrays.asList(item1,item2,item3));

    }
}
