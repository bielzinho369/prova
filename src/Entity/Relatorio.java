package Entity;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Relatorio {
    
    private final String titulo = "\n\n\n\nRelatório de Vendas - Detalhado";
    private final LocalDate periodoIncial;
    private final LocalDate periodoFinal;
    private final List<Venda> vendas;

    public Relatorio(List<Venda> venda, LocalDate periodoInicial, LocalDate periodoFinal){
        this.periodoIncial = periodoInicial;
        this.periodoFinal = periodoFinal;
        this.vendas = venda;
    }

    public Double valorMedio(){
        return this.vendas.stream().mapToDouble(Venda::getValorTotal).sum() / vendas.size();
    }

    public String getTitulo() {
        return this.titulo;
    }

    public LocalDate getPeriodoIncial() {
        return this.periodoIncial;
    }

    public LocalDate getPeriodoFinal() {
        return this.periodoFinal;
    }

    @Override
    public String toString() {
        if(this.vendas.size() != 0){
            return this.titulo + "\n\nPeriodo Emissão: " + this.periodoIncial + " Até " + this.periodoFinal
                    + "\n\nDetalhes:" + this.vendas.stream().map(Venda::toString).collect(Collectors.toList()).get(0)
                    + "\n\nValor médio: " + this.valorMedio();
        }
        return "Nenhuma venda encontrada no período escolhido.";
    }

}
