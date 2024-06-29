package dados;

import java.util.List;
import java.util.stream.Collectors;

public class FiltroTexto implements Filtro{
    private String searchText;

    public FiltroTexto(String searchText) {
        this.searchText = searchText;
    }

    @Override
    public List<Entrada> aplicarFiltro(List<Entrada> entradas) {
        return entradas.stream()
                .filter(entry -> entry.getTexto().contains(searchText))
                .collect(Collectors.toList());
    }
}
