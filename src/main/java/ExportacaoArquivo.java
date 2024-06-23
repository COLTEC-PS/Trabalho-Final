import com.google.gson.Gson;
import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ExportacaoArquivo implements Exportacao {

    @Override
    public void exportarParaJSON(List<Entrada> entradas, String caminhoArquivo) {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(caminhoArquivo)) {
            gson.toJson(entradas, writer);
        } catch (IOException e) {
            System.err.println("Erro ao exportar para JSON: " + e.getMessage());
        }
    }

    @Override
    public void exportarParaCSV(List<Entrada> entradas, String caminhoArquivo) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(caminhoArquivo))) {
            for (Entrada entrada : entradas) {
                String[] linha = {entrada.getTexto(), entrada.getData().toString(), String.join(";", entrada.getCategorias())};
                writer.writeNext(linha);
            }
        } catch (IOException e) {
            System.err.println("Erro ao exportar para CSV: " + e.getMessage());
        }
    }
}
