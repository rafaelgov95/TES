package br.com.analise.compras.Entity;

import br.com.analise.compras.Entity.enumeration.EstadoPagamentoEnum;

public class Pagamento {

    private Long id;
    private EstadoPagamentoEnum estado;
    Pagamento(){

    }

    public Pagamento(Long id, EstadoPagamentoEnum estado) {
        this.id = id;
        this.estado = estado;
    }
}
