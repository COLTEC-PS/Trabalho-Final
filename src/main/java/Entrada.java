import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Entrada {

    private String texto;
    private LocalDate data;
    private List<String> categorias;

    public Entrada(String texto, LocalDate data, List<String > categorias) {
        this.texto = texto;
        this.data = data;
        this.categorias = categorias;
    }

    public static Entrada parse(String linha) {
        String[] partes = linha.split(","); // Separando os valores por vírgulas
        String texto = partes[0];
        LocalDate data = LocalDate.parse(partes[1]); // Convertendo data de String para LocalDate
        List<String> categorias = Arrays.asList(partes[2].split(";")); // Convertendo categorias de String para lista
        return new Entrada(texto, data, categorias);
    }



    // getters and setters

    public String getTexto() {
        return texto;
    }

    public LocalDate getData() {
        return data;
    }

    public List<String> getCategorias() {
        return categorias;
    }

    @Override
    public String toString() {
        return texto + "," + data + "," + String.join(";", categorias);
    }

}
