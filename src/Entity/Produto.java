package Entity;

import java.util.UUID;

public class Produto {
    
    private String id = UUID.randomUUID().toString();
    private Integer codigo;
    private String nome;
    private Double valor;
    private Integer quantidadeEstoque;
    
    public Produto(Integer codigo) {
        this.codigo = codigo;
    }

    public Produto(Integer codigo, String nome, Double valor, Integer quantidadeEstoque) {
        this.codigo = codigo;
        this.nome = nome;
        this.valor = valor;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Integer getCodigo() {
        return codigo;
    }
    public String getNome() {
        return nome;
    }
    public Double getValor() {
        return valor;
    }
    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }
    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public boolean containsIgnoreCase(String name){
        return nome.toLowerCase().contains(name.toLowerCase()) || codigo.toString().contains(name);
    }

    @Override
    public String toString() {
        return "Codigo: " + codigo + "\nNome: " + nome + "\nValor Unitário: " + valor
                + "\nQuantidade em estoque: " + quantidadeEstoque + "\n\n";
    }

}
