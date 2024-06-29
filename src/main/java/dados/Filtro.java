package dados;

import java.util.List;

public interface Filtro {
    List<Entrada> aplicarFiltro(List<Entrada> entradas);
}
