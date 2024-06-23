import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InterfaceUsuario {

    private Scanner scanner;
    private GerenciadorDiario gerenciadorDiario;

    public InterfaceUsuario(GerenciadorDiario gerenciadorDiario) {
        this.scanner = new Scanner(System.in);
        this.gerenciadorDiario = gerenciadorDiario;
    }

    public void exibirMenu() {
        System.out.println("\nDiário em Linha de Comando");
        System.out.println("-------------------------");
        System.out.println("1. Nova Entrada");
        System.out.println("2. Filtrar Entradas");
        System.out.println("3. Exportar Entradas");
        System.out.println("4. Sair");
        System.out.print("Escolha uma opção: ");
    }

    public void iniciar() {
        boolean executando = true;
        while (executando) {
            exibirMenu();
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir nova linha
            switch (opcao) {
                case 1:
                    novaEntrada();
                    break;
                case 2:
                    filtrarEntradas();
                    break;
                case 3:
                    exportarEntradas();
                    break;
                case 4:
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void novaEntrada() {
        System.out.print("Digite o texto da entrada: ");
        String texto = scanner.nextLine();
        System.out.print("Digite a data (dd/MM/yyyy): ");
        String dataStr = scanner.nextLine();
        LocalDate data = ConversorData.stringParaData(dataStr);
        System.out.print("Digite as categorias (separadas por ponto e vírgula): ");
        String categoriasStr = scanner.nextLine();
        List<String> categorias = Arrays.asList(categoriasStr.split(";"));

        gerenciadorDiario.inserirNovaEntrada(texto, data, categorias);
        System.out.println("Entrada adicionada com sucesso!");
    }

    private void filtrarEntradas() {
        System.out.print("Digite o texto para filtro (ou deixe em branco): ");
        String texto = scanner.nextLine();
        System.out.print("Digite a data inicial (dd/MM/yyyy) ou deixe em branco: ");
        String dataInicioStr = scanner.nextLine();
        LocalDate dataInicio = dataInicioStr.isEmpty() ? null : ConversorData.stringParaData(dataInicioStr);
        System.out.print("Digite a data final (dd/MM/yyyy) ou deixe em branco: ");
        String dataFimStr = scanner.nextLine();
        LocalDate dataFim = dataFimStr.isEmpty() ? null : ConversorData.stringParaData(dataFimStr);
        System.out.print("Digite as categorias (separadas por ponto e vírgula) ou deixe em branco: ");
        String categoriasStr = scanner.nextLine();
        List<String> categorias = categoriasStr.isEmpty() ? null : Arrays.asList(categoriasStr.split(";"));

        List<Entrada> entradas = gerenciadorDiario.filtrarEntradas(texto, dataInicio, dataFim, categorias);
        System.out.println("Entradas filtradas:");
        for (Entrada entrada : entradas) {
            System.out.println(entrada);
        }
    }

    private void exportarEntradas() {
        System.out.print("Digite o formato de exportação (JSON ou CSV): ");
        String formato = scanner.nextLine();
        System.out.print("Digite o caminho do arquivo para exportação: ");
        String caminhoArquivo = scanner.nextLine();

        List<Entrada> entradas = gerenciadorDiario.filtrarEntradas(null, null, null, null);
        gerenciadorDiario.exportarEntradas(entradas, formato, caminhoArquivo);
        System.out.println("Entradas exportadas com sucesso!");
    }

}