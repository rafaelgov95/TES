package br.com.analise.compras.Entity;

import br.com.analise.compras.Entity.enumeration.EstadoPagamentoEnum;

import javax.persistence.*;

@Entity
@Table(name="tb_pagamento")
@SequenceGenerator(name = "seq_pagamento", sequenceName = "seq_pagamento")
public class Pagamento {
    @Id
    @Column(name="pg_id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_pagamento")
    private Long id;

    @Column(name="pg_estado")
    private EstadoPagamentoEnum estado;

    @OneToOne
    @JoinColumn(name="pe_id")
    @MapsId
    private Pedido pedido;


    Pagamento(){

    }

    public Pagamento(Long id, EstadoPagamentoEnum estado, Pedido pedido) {
        this.id = id;
        this.estado = estado;
        this.pedido=pedido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EstadoPagamentoEnum getEstado() {
        return estado;
    }

    public void setEstado(EstadoPagamentoEnum estado) {
        this.estado = estado;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
