package br.com.analise.compras.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name="tb_pedido")
@SequenceGenerator(name = "seq_pedido", sequenceName = "seq_pedido")
public class Pedido implements Serializable {
    @Id
    @Column(name="pe_id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_pedido")
    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name="pe_instante")
    private Date instante;

    @JoinColumn(name = "en_id")
    private Endereco enderecoDoEntrega;


    @ManyToOne
    @JoinColumn(name = "cl_id")
    private Cliente cliente;


    @OneToOne(cascade = CascadeType.ALL,mappedBy = "pedido")
    private Pagamento pagamento ;

    @OneToMany(mappedBy="id.pedido")
    private Set<ItemPedido> itens = new HashSet<>();


    public void setItens(Set<ItemPedido> itens) {
        this.itens = itens;
    }

    public Pedido(){

    }

    public Pedido(Long id, Date instante, Endereco enderecoDoEntrega, Cliente cliente) {
        this.id = id;
        this.instante = instante;
        this.enderecoDoEntrega = enderecoDoEntrega;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getInstante() {
        return instante;
    }

    public void setInstante(Date instante) {
        this.instante = instante;
    }

    public Endereco getEnderecoDoEntrega() {
        return enderecoDoEntrega;
    }

    public void setEnderecoDoEntrega(Endereco enderecoDoEntrega) {
        this.enderecoDoEntrega = enderecoDoEntrega;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public Set<ItemPedido> getItens() {
        return itens;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(id, pedido.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
