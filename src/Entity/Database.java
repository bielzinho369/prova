package Entity;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Database {

    private List<Produto> produtosCadastrados = new ArrayList<>();
    
    private List<Venda> vendasRealizadas = new ArrayList<>();

    public List<Produto> getAllProdutosCadastrados() {
        return produtosCadastrados
            .stream().filter(Objects::nonNull)
            .collect(Collectors.toList());
    }

    public List<Venda> getAllVendasRealizadas() {
        return vendasRealizadas;
    }

    public List<Produto> getProdutoByCodeOrName(String string){
        return produtosCadastrados.stream()
            .filter(it -> it.containsIgnoreCase(string))
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
    }

    public List<Venda> getVendasByPeriod(LocalDate dataInicial, LocalDate dataFinal){
        return vendasRealizadas.stream()
            .filter(it -> it.getDataVenda().isBefore(dataFinal) && it.getDataVenda().isAfter(dataInicial))
            .collect(Collectors.toList());
    }

    public void cadastrarProduto(Produto produto) {
        this.produtosCadastrados.add(produto);
    }

    public void finalizarVenda(Venda venda) {
        venda.finalizarVenda();
        this.vendasRealizadas.add(venda);
    }

    public Integer getQuantidadeProdutos(){
        return produtosCadastrados.size();
    }

    public void removeProduto(Produto produto){
        produtosCadastrados.remove(produto);
    }

}
