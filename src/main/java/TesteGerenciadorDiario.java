import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class TesteGerenciadorDiario {
    private GerenciadorDiario gerenciadorDiario;

    @BeforeClass
    public void setup() {
        Persistencia persistencia = PersistenciaArquivoTexto.getInstance("diario.txt");
        Exportacao exportacao = new ExportacaoArquivo();
        Diario diario = new Diario(persistencia, exportacao);
        gerenciadorDiario = new GerenciadorDiario(diario);

        // Adiciona entradas de exemplo
        gerenciadorDiario.inserirNovaEntrada("Texto com substringExistente", LocalDate.of(2023, 6, 23), Arrays.asList("categoriaExistente"));
        gerenciadorDiario.inserirNovaEntrada("Texto sem a substring", LocalDate.of(2023, 6, 22), Arrays.asList("outraCategoria"));
    }

    @Test
    public void testeFiltroTexto() {
        List<Entrada> resultado = gerenciadorDiario.filtrarEntradas("substringExistente", null, null, null);
        Assert.assertFalse(resultado.isEmpty(), "Deveria ter encontrado pelo menos uma entrada");
    }

    @Test
    public void testeFiltroData() {
        List<Entrada> resultado = gerenciadorDiario.filtrarEntradas(null, LocalDate.of(2023, 6, 23), LocalDate.of(2023, 6, 23), null);
        Assert.assertFalse(resultado.isEmpty(), "Deveria ter encontrado pelo menos uma entrada");
    }

    @Test
    public void testeFiltroCategoria() {
        List<Entrada> resultado = gerenciadorDiario.filtrarEntradas(null, null, null, Arrays.asList("categoriaExistente"));
        Assert.assertFalse(resultado.isEmpty(), "Deveria ter encontrado pelo menos uma entrada");
    }

    @Test
    public void testeFiltroCombinado() {
        List<Entrada> resultado = gerenciadorDiario.filtrarEntradas("substringExistente", LocalDate.of(2023, 6, 23), LocalDate.of(2023, 6, 23), Arrays.asList("categoriaExistente"));
        Assert.assertFalse(resultado.isEmpty(), "Deveria ter encontrado pelo menos uma entrada");
    }
}
