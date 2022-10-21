package Entity;

public class ProdutoVenda {
    
    private final String id;
    private Produto produto;
    private Integer quantidade;
    private Double valorTotal;

    public ProdutoVenda(Produto produto, Integer quantidade){
        this.id = produto.getId();
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public String getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public String toString() {
        return "\n\nNome do Produto: " + produto.getNome() + "\nQuantidade: " + quantidade + "\nValor unitário: "
                + produto.getValor();
    }

}
