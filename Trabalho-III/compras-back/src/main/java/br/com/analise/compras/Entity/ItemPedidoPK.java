package br.com.analise.compras.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ItemPedidoPK implements Serializable {

    @JoinColumn(name="pe_id")
    @ManyToOne
    private Pedido pedido;

    @JoinColumn(name="pr_id")
    @ManyToOne
    private Produto produto;

    public ItemPedidoPK() {

    }
    public ItemPedidoPK(Pedido pedido,Produto produto) {
        this.pedido=pedido;
        this.produto=produto;

    }



    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }


}
