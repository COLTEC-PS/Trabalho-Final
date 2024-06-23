import java.time.LocalDate;
import java.util.List;

public interface Persistencia {

    void salvar(Entrada entrada);
    List<Entrada> carregarTodas();
    List<Entrada> carregarPorData(LocalDate data);
    List<Entrada> carregarPorCategoria(String categoria);
    List<Entrada> carregarPorTexto(String texto);


}
