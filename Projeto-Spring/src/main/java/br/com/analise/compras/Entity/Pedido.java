package br.com.analise.compras.Entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Table(name="tb_pedido")
@SequenceGenerator(name = "seq_pedido", sequenceName = "seq_pedido")
public class Pedido implements Serializable {
    @Id
    @Column(name="pe_id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_endereco")
    private Long id;
    @Column(name="pe_instante")
    private Date instante;

    @JoinColumn(name = "en_id")
    private Endereco enderecoDoEntrega;


    @ManyToOne
    @JoinColumn(name = "cl_id")
    private Cliente cliente;

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

    public Pedido(){

    }

    public Pedido(Long id, Date instante, Endereco enderecoDoEntrega, Cliente cliente) {
        this.id = id;
        this.instante = instante;
        this.enderecoDoEntrega = enderecoDoEntrega;
        this.cliente = cliente;
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
