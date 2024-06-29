package dados;

public class ExportacaoFactory {
    public static Exportacao getExporter(){
        return new ExportacaoJson();
    }
}
