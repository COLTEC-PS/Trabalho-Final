import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorDiario {

    private Diario diario;

    public GerenciadorDiario(Diario diario) {
        this.diario = diario;
    }

    public void inserirNovaEntrada(String texto, LocalDate data, List<String> categorias) {
        Entrada novaEntrada = new Entrada(texto, data, categorias);
        diario.getEntradas().add(novaEntrada);
        diario.getPersistencia().salvar(novaEntrada);
    }

    public List<Entrada> filtrarEntradas(String texto, LocalDate dataInicio, LocalDate dataFim, List<String> categorias) {
        List<Entrada> entradasFiltradas = new ArrayList<>();

        for (Entrada entrada : diario.getEntradas()) {
            // Filtrar por texto, se fornecido
            if (texto != null && !texto.isEmpty()) {
                if (!entrada.getTexto().contains(texto)) {
                    continue; // Não corresponde ao texto, passa para a próxima entrada
                }
            }

            // Filtrar por data, se fornecido
            if (dataInicio != null && dataFim != null) {
                LocalDate dataEntrada = entrada.getData();
                if (dataEntrada.isBefore(dataInicio) || dataEntrada.isAfter(dataFim)) {
                    continue; // Fora do intervalo de datas, passa para a próxima entrada
                }
            }

            // Filtrar por categorias, se fornecidas
            if (categorias != null && !categorias.isEmpty()) {
                List<String> categoriasEntrada = entrada.getCategorias();
                boolean categoriaEncontrada = false;
                for (String categoria : categorias) {
                    if (categoriasEntrada.contains(categoria)) {
                        categoriaEncontrada = true;
                        break; // Encontrou a categoria, pode incluir a entrada
                    }
                }
                if (!categoriaEncontrada) {
                    continue; // Não corresponde às categorias, passa para a próxima entrada
                }
            }

            // Se passou por todos os filtros, inclui a entrada na lista filtrada
            entradasFiltradas.add(entrada);
        }

        return entradasFiltradas;
    }

    public void exportarEntradas(List<Entrada> entradas, String formato, String caminhoArquivo) {
        if (formato.equalsIgnoreCase("JSON")) {
            diario.getExportacao().exportarParaJSON(entradas, caminhoArquivo);
        } else if (formato.equalsIgnoreCase("CSV")) {
            diario.getExportacao().exportarParaCSV(entradas, caminhoArquivo);
        } else {
            System.out.println("Formato de exportação inválido!");
        }
    }
}
