package br.com.analise.compras.Entity;

import br.com.analise.compras.Entity.enumeration.EstadoPagamentoEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="tb_pagamento_com_boleto")
public class PagamentoBoleto extends Pagamento{
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name="data_vencimento")
    private Date dataVencimento;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name="data_pagamento")
    private Date dataPagamento;



    public PagamentoBoleto() {
        super();

    }

    public PagamentoBoleto(Long id, EstadoPagamentoEnum estado, Pedido pedido, Date dataVencimento) {
        super(id, estado, pedido);
        this.dataVencimento = dataVencimento;
    }
}
