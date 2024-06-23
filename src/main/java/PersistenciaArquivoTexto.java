import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaArquivoTexto implements Persistencia {

    private static PersistenciaArquivoTexto instance;
    private final String arquivoDiario;

    PersistenciaArquivoTexto(String arquivoDiario) {
        this.arquivoDiario = arquivoDiario;
    }

    public static synchronized PersistenciaArquivoTexto getInstance(String arquivoDiario) {
        if (instance == null) {
            instance = new PersistenciaArquivoTexto(arquivoDiario);
        }
        return instance;
    }

    @Override
    public void salvar(Entrada entrada) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(arquivoDiario), true))) {
            writer.write(entrada.toString() + "\n");
        } catch (IOException e) {
            System.err.println("Erro ao salvar entrada: " + e.getMessage());
        }
    }

    @Override
    public List<Entrada> carregarTodas() {
        List<Entrada> entradas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(arquivoDiario)))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Entrada entrada = Entrada.parse(linha);
                entradas.add(entrada);
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar entradas: " + e.getMessage());
        }
        return entradas;
    }

    @Override
    public List<Entrada> carregarPorData(LocalDate data) {
        List<Entrada> entradas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(arquivoDiario)))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Entrada entrada = Entrada.parse(linha);
                if (entrada.getData().equals(data)) {
                    entradas.add(entrada);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar entradas por data: " + e.getMessage());
        }
        return entradas;
    }

    @Override
    public List<Entrada> carregarPorCategoria(String categoria) {
        List<Entrada> entradas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(arquivoDiario)))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Entrada entrada = Entrada.parse(linha);
                if (entrada.getCategorias().contains(categoria)) {
                    entradas.add(entrada);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar entradas por categoria: " + e.getMessage());
        }
        return entradas;
    }

    @Override
    public List<Entrada> carregarPorTexto(String texto) {
        List<Entrada> entradas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(arquivoDiario)))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Entrada entrada = Entrada.parse(linha);
                if (entrada.getTexto().contains(texto)) {
                    entradas.add(entrada);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar entradas por texto: " + e.getMessage());
        }
        return entradas;
    }
}
