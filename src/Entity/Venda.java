package Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Venda {
    
    private String id = UUID.randomUUID().toString();
    private final List<ProdutoVenda> produtos = new ArrayList<>();
    private LocalDate dataVenda;
    private Boolean finalizada = false;

    public Venda(){
    }

    public void inserirProduto(Produto produto, Integer quantidade){
        produtos.add(new ProdutoVenda(produto, quantidade));
    }

    public void removerProduto(Produto produto){
        produtos.stream()
            .filter(it -> it.getId().equals(produto.getId()))
            .collect(Collectors.toList())
            .get(0);
    }

    public Double getValorTotal(){
        return produtos.stream()
            .mapToDouble(it -> it.getProduto().getValor()*it.getQuantidade())
            .sum();
    }

    public void finalizarVenda(){
        this.dataVenda = LocalDate.now();
        this.finalizada = true;
    }

    public String getId() {
        return this.id;
    }

    public List<ProdutoVenda> getProdutos() {
        return this.produtos;
    }

    public LocalDate getDataVenda() {
        return this.dataVenda;
    }

    public Boolean getFinalizada() {
        return this.finalizada;
    }

    @Override
    public String toString() {
        return "\n\nData da Venda: " + this.dataVenda + this.produtos.stream().map(ProdutoVenda::toString).collect(Collectors.toList());
    }

    
}
