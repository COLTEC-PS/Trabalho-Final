import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ConversorData {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static LocalDate stringParaData(String dataStr) {
        return LocalDate.parse(dataStr, FORMATTER);
    }

    public static String dataParaString(LocalDate data) {
        return data.format(FORMATTER);
    }
}
