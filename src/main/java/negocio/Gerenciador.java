package negocio;

import auxiliares.Auxiliar;
import dados.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class Gerenciador {
    private Armazenamento armazenamento;

    public Gerenciador() {
        this.armazenamento = Armazenamento.getInstance();
    }

    //(a) -> inserir nova entrada
    public void adicionarEntrada(String texto, Date date, List<String> categories) throws IOException {
        Entrada entry = new Entrada(texto, date, categories);
        armazenamento.adicionarEntrada(entry);
    }

    //(b) - filtrar entradas com os critérios
    public List<Entrada> filtrarEntradas(Filtro... filtros) {
        List<Entrada> entradas = armazenamento.getEntradasDiario();
        for (Filtro filtro : filtros) {
            entradas = filtro.aplicarFiltro(entradas);
        }
        return entradas;
    }

    //(c) -> exportar as entradas para arquivo externo
    public void exportarEntradas(String arquivo) {
        Exportacao exporter = ExportacaoFactory.getExporter();
        exporter.exportar(armazenamento.getEntradasDiario(), arquivo);
    }

    public List<Entrada> listar() {
        return armazenamento.getEntradasDiario();
    }

    ///conversão de dados fornecidos pelo usuário para objetos Java dentro do programa
    public String formateDate(Date date){
        return Auxiliar.formatDate(date);
    }

    public Date parseDate(String date) throws ParseException {
        return Auxiliar.parseDate(date);
    }


}
