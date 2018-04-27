package br.com.analise.compras.repository;

import br.com.analise.compras.Entity.ItemPedido;
import br.com.analise.compras.Entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
}
