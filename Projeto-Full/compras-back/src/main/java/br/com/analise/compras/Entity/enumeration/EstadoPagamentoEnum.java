package br.com.analise.compras.Entity.enumeration;

public enum EstadoPagamentoEnum {

    PENDENTE("Pendente"),

    QUITADO("Quitado"),

    CANCELADO("Cancelado");

    String descricao;

    EstadoPagamentoEnum(String descricao) {
        this.descricao = descricao;
    }


    public String getEstadoPagamento() {
        return this.descricao;
    }
}
