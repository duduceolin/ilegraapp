package br.com.ilegra.ilegraapp.bean.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author dudu
 */
public class Vendedor implements Comparable<Vendedor>, Serializable {

    private static final long serialVersionUID = -8637996436289396149L;

    private String cpf;
    private String nome;
    private BigDecimal salario;
    private List<Venda> vendas;
    private BigDecimal totalVendas;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }

    public BigDecimal getTotalVendas() {
        return totalVendas;
    }

    public void setTotalVendas(BigDecimal totalVendas) {
        this.totalVendas = totalVendas;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.cpf);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vendedor other = (Vendedor) obj;
        if (!Objects.equals(this.cpf, other.cpf)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Vendedor{" + "cpf=" + cpf + ", nome=" + nome + ", salario=" + salario + ", vendas=" + vendas + ", totalVendas=" + totalVendas + '}';
    }

    @Override
    public int compareTo(Vendedor o) {
        return totalVendas.compareTo(o.getTotalVendas());
    }

}
