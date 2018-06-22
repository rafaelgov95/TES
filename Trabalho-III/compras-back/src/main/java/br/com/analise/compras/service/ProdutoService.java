package br.com.analise.compras.service;

import br.com.analise.compras.Entity.Categoria;
import br.com.analise.compras.Entity.Produto;
import br.com.analise.compras.exception.ObjectNotFountException;
import br.com.analise.compras.repository.CategoriaRepository;
import br.com.analise.compras.repository.ProdutoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaoRepository;

    public Produto buscarPedidoPorId(Long id) {

        Produto produto= produtoRepository.findOne(id);

        if(produto == null){
            throw new ObjectNotFountException("O objeto " + Categoria.class.getName() +
            " com ID: " + id + " não foi encontrado!");
        }

        return produto;
    }


    private final Logger log = LoggerFactory.getLogger(this.getClass());
    public Produto salvar(Produto produto) {
        return  produtoRepository.save(produto);
    }
}
