public class Main {

    public static void main(String[] args) {
        Persistencia persistencia = new PersistenciaArquivoTexto("diario.txt");
        Exportacao exportacao = new ExportacaoArquivo();

        Diario diario = new Diario(persistencia, exportacao);
        GerenciadorDiario gerenciador = new GerenciadorDiario(diario);
        InterfaceUsuario ui = new InterfaceUsuario(gerenciador);

        ui.iniciar();
    }
}
