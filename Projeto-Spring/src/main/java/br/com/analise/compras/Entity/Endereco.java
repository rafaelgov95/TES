package br.com.analise.compras.Entity;



import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="tb_endereco")
@SequenceGenerator(name = "seq_endereco", sequenceName = "seq_endereco")
public class Endereco implements Serializable {
    @Id
    @Column(name="en_id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_endereco")
    private Long id;

    @Column(name="en_logradouro")
    private String logradouro;

    @Column(name="en_numero")
    private String numero;

    @Column(name="en_complemento")
    private String complemento;

    @Column(name="en_bairro")
    private String bairro;

    @Column(name="en_cep")
    private String cep;

    @ManyToOne
    @JoinColumn(name="cl_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name="ci_id")
    private Cidade cidade;


    public Endereco(){}

    public Endereco(Long id, String logradouro, String numero, String complemento, String bairro, String cep, Cliente cliente, Cidade cidade) {
        this.id = id;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cep = cep;
        this.cliente=cliente;
        this.cidade=cidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Endereco endereco = (Endereco) o;
        return Objects.equals(id, endereco.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
