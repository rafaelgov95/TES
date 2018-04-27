package br.com.analise.compras.Entity;

import br.com.analise.compras.Entity.enumeration.EstadoPagamentoEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="tb_pagamento_com_cartao")
public class PagamentoComCartao extends Pagamento{

    @Column(name="pg_n_parcelas")
    private Integer numeroDeParclas;

    public PagamentoComCartao(Integer numeroDeParclas) {
        super();
        this.numeroDeParclas = numeroDeParclas;
    }

    public PagamentoComCartao(Long id, EstadoPagamentoEnum estado, Pedido pedido, Integer numeroDeParclas) {
        super(id, estado, pedido);
        this.numeroDeParclas = numeroDeParclas;
    }
}
