package br.com.analise.compras.service;

import br.com.analise.compras.Entity.Categoria;
import br.com.analise.compras.Entity.Pedido;
import br.com.analise.compras.exception.ObjectNotFountException;
import br.com.analise.compras.repository.CategoriaRepository;
import br.com.analise.compras.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido buscarPedidoPorId(Long id) {

        Pedido pedido = pedidoRepository.findOne(id);

        if(pedido == null){
            throw new ObjectNotFountException("O objeto " + Categoria.class.getName() +
            " com ID: " + id + " não foi encontrado!");
        }

        return pedido;
    }
}
