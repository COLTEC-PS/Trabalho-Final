package dados;

import java.util.ArrayList;
import java.util.List;

public class FiltrosCompostos implements Filtro{

    private List<Filtro> filters = new ArrayList<>();

    public void addFilter(Filtro filter) {
        filters.add(filter);
    }
    @Override
    public List<Entrada> aplicarFiltro(List<Entrada> entradas) {
        for (Filtro filter : filters) {
            entradas = filter.aplicarFiltro(entradas);
        }
        return entradas;
    }
}
