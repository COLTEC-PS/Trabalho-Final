
import java.util.ArrayList;
import java.util.List;

public class Diario {

    private List<Entrada> entradas;
    private Persistencia persistencia;
    private Exportacao exportacao;

    public Diario(Persistencia persistencia, Exportacao exportacao) {
        this.entradas = new ArrayList<>();
        this.persistencia = persistencia;
        this.exportacao = exportacao;
    }

    public List<Entrada> getEntradas() {
        return entradas;
    }

    public Persistencia getPersistencia() {
        return persistencia;
    }

    public Exportacao getExportacao() {
        return exportacao;
    }
}
