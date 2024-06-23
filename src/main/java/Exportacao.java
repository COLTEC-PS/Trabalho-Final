import java.util.List;

public interface Exportacao {

    void exportarParaJSON(List<Entrada> entradas, String caminhoArquivo);
    void exportarParaCSV(List<Entrada> entradas, String caminhoArquivo);

}
