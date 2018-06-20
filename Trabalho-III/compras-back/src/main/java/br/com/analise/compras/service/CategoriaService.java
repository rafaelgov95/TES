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
public class CategoriaService {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public Categoria salvar(Categoria cat1) {
        Categoria catsalva = categoriaRepository.save(cat1);
        List<Produto> produtos = cat1.getProdutos();
        produtos.forEach(produto -> produto.getCategorias().add(catsalva));
        produtoRepository.save(cat1.getProdutos());
        return catsalva;
    }
    public Categoria buscarCategoriaPorId(Long id) {

        Categoria categoria = categoriaRepository.findOne(id);

        if(categoria == null){
            throw new ObjectNotFountException("O objeto " + Categoria.class.getName() +
            " com ID: " + id + " não foi encontrado!");
        }

        return categoria;
    }
    public List<Categoria> getCategorias() {

        List<Categoria> categorias = categoriaRepository.findAll();

        if(categorias == null){
            throw new ObjectNotFountException("O objeto não foi encontrado!");
        }
        return categorias;
    }
    public void delete(Long id) {

        categoriaRepository.delete(id);
    }
}
