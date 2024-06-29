package dados;

import java.util.List;

public interface Exportacao {
    void exportar(List<Entrada> entradas, String arquivo);
}
