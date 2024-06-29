package dados;

import java.util.List;
import java.util.stream.Collectors;

public class FiltroCategoria implements Filtro{
    private String categoria;

    public FiltroCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public List<Entrada> aplicarFiltro(List<Entrada> entradas) {
        return entradas.stream()
                .filter(entry -> entry.getCategorias().contains(categoria))
                .collect(Collectors.toList());
    }
}
