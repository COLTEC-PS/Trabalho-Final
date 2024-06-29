package apresentacao;

import negocio.Gerenciador;
import dados.*;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

public class MenuUsuario {
    private Gerenciador gerenciador;
    private Scanner scanner;

    public MenuUsuario() {
        this.gerenciador = new Gerenciador();
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() throws IOException, ParseException {
        while (true) {
            System.out.println("Bem vindo ao Journaling");
            System.out.println("1. Adicionar nova entrada");
            System.out.println("2. Listar entradas");
            System.out.println("3. Filtrar as entradas");
            System.out.println("4. Exportar as entradas para JSON");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    adicionaEntradas();
                    break;
                case "2":
                    listaEntradas();
                    break;
                case "3":
                    filtraEntrdas();
                    break;
                case "4":
                    exportarEntradas();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Escolha inválida. Por favor, tente novamente.");
            }
        }
    }

    private void adicionaEntradas() throws IOException, ParseException {
        System.out.print("Digite aqui a entrada: ");
        String texto = scanner.nextLine();

        System.out.print("Digite a data (yyyy-MM-dd): ");
        String dateStr = scanner.nextLine();
        Date dateDate = gerenciador.parseDate(dateStr);

        System.out.println("Indique as categorias para classificar sua entrada (separadas por vírgulas): ");
        System.out.print("Exemplos de categorias: DEBUG, REVISÃO, REFATORAÇÃO, URGENTE, REUNIÃO, PRONTO, DEPOIS ");
        String categoriasStr = scanner.nextLine();
        List<String> categorias = Arrays.asList(categoriasStr.split("\\s*,\\s*"));

        gerenciador.adicionarEntrada(texto, dateDate, categorias);
    }

    private void listaEntradas() {
        List<Entrada> entries = gerenciador.listar();
        Diario.mostrarEntradas(entries);
    }

    private void filtraEntrdas() {
        while (true) {
            System.out.println("Escolha uma opção de filtro:");
            System.out.println("1. Filtrar por Texto");
            System.out.println("2. Filtrar por Data");
            System.out.println("3. Filtrar por Categoria");
            System.out.println("4. Filtro Composto");
            System.out.println("5. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    filterByText();
                    return;
                case "2":
                    filterByDate();
                    return;
                case "3":
                    filterByCategory();
                    return;
                case "4":
                    compositeFilterMenu();
                    return;
                case "5":
                    return;
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
            }
        }
    }

    private void filterByText() {
        System.out.print("Digite o texto para filtrar: ");
        String texto = scanner.nextLine();
        List<Filtro> filtros = new ArrayList<>();
        if (!texto.isEmpty()) {
            filtros.add(new FiltroTexto(texto));
        }
        List<Entrada> entradas = gerenciador.filtrarEntradas(filtros.toArray(new Filtro[0]));
        Diario.mostrarEntradas(entradas);
    }

    private void filterByDate() {
        System.out.print("Digite a data de início (yyyy-MM-dd): ");
        String dataInicialStr = scanner.nextLine();
        System.out.print("Digite a data final (yyyy-MM-dd): ");
        String dataFinalStr = scanner.nextLine();
        try {
            Date dataInicial = gerenciador.parseDate(dataInicialStr);
            Date dataFinal = gerenciador.parseDate(dataFinalStr);
            List<Filtro> filtros = new ArrayList<>();
            filtros.add(new FiltroData(dataInicial, dataFinal));
            List<Entrada> entradas = gerenciador.filtrarEntradas(filtros.toArray(new Filtro[0]));
            Diario.mostrarEntradas(entradas);
        } catch (ParseException e) {
            System.out.println("Formato de data enviado é inválido. Filtro não foi aplicado.");
        }
    }

    private void filterByCategory() {
        System.out.print("Digite a categoria para filtrar: ");
        String categoria = scanner.nextLine();
        List<Filtro> filtros = new ArrayList<>();
        if (!categoria.isEmpty()) {
            filtros.add(new FiltroCategoria(categoria));
        }
        List<Entrada> entradas = gerenciador.filtrarEntradas(filtros.toArray(new Filtro[0]));
        Diario.mostrarEntradas(entradas);
    }

    private void compositeFilterMenu() {
        while (true) {
            System.out.println("Escolha uma opção de filtro composto:");
            System.out.println("1. Filtro Texto & Data");
            System.out.println("2. Filtro Texto & Categoria");
            System.out.println("3. Filtro Categoria & Data");
            System.out.println("4. Voltar ao menu de filtro");
            System.out.print("Escolha uma opção: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    compositeFilterByTextAndDate();
                    return;
                case "2":
                    compositeFilterByTextAndCategory();
                    return;
                case "3":
                    compositeFilterByCategoryAndDate();
                    return;
                case "4":
                    return;
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
            }
        }
    }

    private void compositeFilterByTextAndDate() {
        System.out.print("Digite o texto para filtrar: ");
        String text = scanner.nextLine();
        System.out.print("Digite a data de início (yyyy-MM-dd): ");
        String startDateStr = scanner.nextLine();
        System.out.print("Digite a data final (yyyy-MM-dd): ");
        String endDateStr = scanner.nextLine();

        try {
            Date dataInicial = gerenciador.parseDate(startDateStr);
            Date dataFinal = gerenciador.parseDate(endDateStr);
            FiltrosCompostos compositeFilter = new FiltrosCompostos();
            compositeFilter.addFilter(new FiltroTexto(text));
            compositeFilter.addFilter(new FiltroData(dataInicial, dataFinal));

            List<Entrada> entradas = gerenciador.filtrarEntradas(compositeFilter);
            Diario.mostrarEntradas(entradas);
        } catch (ParseException e) {
            System.out.println("Formato de data inserido é inválido. Filtro não foi aplicado.");
        }
    }

    private void compositeFilterByTextAndCategory() {
        System.out.print("Digite o texto para filtrar: ");
        String text = scanner.nextLine();
        System.out.print("Digite a categoria para filtrar: ");
        String category = scanner.nextLine();

        FiltrosCompostos compositeFilter = new FiltrosCompostos();
        compositeFilter.addFilter(new FiltroTexto(text));
        compositeFilter.addFilter(new FiltroCategoria(category));

        List<Entrada> entries = gerenciador.filtrarEntradas(compositeFilter);
        Diario.mostrarEntradas(entries);
    }

    private void compositeFilterByCategoryAndDate() {
        System.out.print("Digite a categoria para filtrar: ");
        String category = scanner.nextLine();
        System.out.print("Digite a data de início (yyyy-MM-dd): ");
        String startDateStr = scanner.nextLine();
        System.out.print("Digite a data final (yyyy-MM-dd): ");
        String endDateStr = scanner.nextLine();

        try {
            Date dataInicial = gerenciador.parseDate(startDateStr);
            Date dataFinal = gerenciador.parseDate(endDateStr);
            FiltrosCompostos compositeFilter = new FiltrosCompostos();
            compositeFilter.addFilter(new FiltroCategoria(category));
            compositeFilter.addFilter(new FiltroData(dataInicial, dataFinal));

            List<Entrada> entries = gerenciador.filtrarEntradas(compositeFilter);
            Diario.mostrarEntradas(entries);
        } catch (ParseException e) {
            System.out.println("Formato de data inválido. Filtro não aplicado.");
        }
    }

    private void exportarEntradas() {
        System.out.print("Digite o nome do arquivo: ");
        String filename = scanner.nextLine();

        gerenciador.exportarEntradas(filename);
        System.out.println("Entradas exportadas com sucesso.");
    }
}
