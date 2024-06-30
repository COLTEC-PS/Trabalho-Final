package View;

import Controller.JournalController;
import ModelData.JournalEntry;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        System.out.println("Seja bem vindo ao Journaling.");
        Menu.options();
    }
    private static final JournalController controller = new JournalController();

    public static void options() {
        boolean isRunning = true;
        Scanner scan = new Scanner(System.in);

        while (isRunning) {
            showOptions();
            int option = scan.nextInt();
            scan.nextLine();

            switch (option) {
                case 1:
                    newEntry(scan);
                    break;
                case 2:
                    showNewEntries();
                    break;
                case 3:
                    filterEntries(scan);
                    break;
                case 4:
                    exportEntries();
                    break;
                case 5:
                    isRunning = false;
                    System.out.println("Até logo!");
                    break;
                default:
                    System.out.println("Escolha uma opção válida!");
            }
        }
        scan.close();
    }


    private static void newEntry(Scanner scan) {
        System.out.println("Digite aqui a entrada:");
        System.out.print(">> ");
        String entry = scan.nextLine();
        System.out.println("Escolha a categoria da nova entrada:");
        String category = categorySelector(scan);
        controller.addEntry(entry, category);
        System.out.println("Entrada criada com sucesso!");
    }

    private static void showNewEntries() {
        List<JournalEntry> entries = controller.getEntries();
        if (entries.isEmpty()) {
            System.out.println("Nenhuma nova entrada encontrada.");
        } else {
            System.out.println("Novas entradas:");
            for (JournalEntry entry : entries) {
                System.out.println(entry);
            }
        }
    }

    private static void filterEntries(Scanner scan) {
        showFilterOptions();
        int option = scan.nextInt();
        scan.nextLine();

        switch (option) {
            case 1:
                filterBySubstring(scan);
                break;
            case 2:
                filterByDate(scan);
                break;
            case 3:
                filterByCategory(scan);
                break;
            case 4:
                filterBySubstringAndDate(scan);
                break;
            case 5:
                filterBySubstringAndCategory(scan);
                break;
            case 6:
                filterByDateAndCategory(scan);
                break;
            case 7:
                filterByEverything(scan);
                break;
            default:
                System.out.println("Opção inválida.");
                break;
        }
    }

    private static void filterBySubstring(Scanner scan) {
        System.out.println("Insira a substring que deseja procurar:");
        System.out.print(">> ");
        String substring = scan.nextLine();

        List<JournalEntry> filteredBySubstring = controller.filterEntries(1, substring, null, null);
        showFilteredEntries(filteredBySubstring);
    }

    private static void filterByDate(Scanner scan) {
        System.out.println("Insira a data (no formato dd-MM-aaaa) que deseja procurar:");
        System.out.print(">> ");
        String dateString = scan.nextLine();
        Date date = parseDate(dateString);

        List<JournalEntry> filteredByDate = controller.filterEntries(2, null, date, null);
        showFilteredEntries(filteredByDate);
    }

    private static void filterByCategory(Scanner scan) {
        String category = categorySelector(scan);

        List<JournalEntry> filteredByCategory = controller.filterEntries(3, null, null, category);
        showFilteredEntries(filteredByCategory);
    }

    private static void filterBySubstringAndDate(Scanner scan) {
        System.out.println("Insira a substring que deseja procurar:");
        System.out.print(">> ");
        String substring = scan.nextLine();

        System.out.println("Insira a data (no formato dd-MM-aaaa) que deseja procurar:");
        System.out.print(">> ");
        String dateString = scan.nextLine();
        Date date = parseDate(dateString);

        List<JournalEntry> filteredBySubstringAndDate = controller.filterEntries(4, substring, date, null);
        showFilteredEntries(filteredBySubstringAndDate);
    }

    private static void filterBySubstringAndCategory(Scanner scan) {
        System.out.println("Insira a substring que deseja procurar:");
        System.out.print(">> ");
        String substring = scan.nextLine();

        String category = categorySelector(scan);

        List<JournalEntry> filteredBySubstringAndCategory = controller.filterEntries(5, substring, null, category);
        showFilteredEntries(filteredBySubstringAndCategory);
    }

    private static void filterByDateAndCategory(Scanner scan) {
        System.out.println("Insira a data (no formato dd-MM-aaaa) que deseja procurar:");
        System.out.print(">> ");
        String dateString = scan.nextLine();
        Date date = parseDate(dateString);

        String category = categorySelector(scan);

        List<JournalEntry> filteredByDateAndCategory = controller.filterEntries(6, null, date, category);
        showFilteredEntries(filteredByDateAndCategory);
    }

    private static void filterByEverything(Scanner scan) {
        System.out.println("Insira a substring que deseja procurar:");
        System.out.print(">> ");
        String substring = scan.nextLine();

        System.out.println("Insira a data (no formato dd-MM-aaaa) que deseja procurar:");
        System.out.print(">> ");
        String dateString = scan.nextLine();
        Date date = parseDate(dateString);

        String category = categorySelector(scan);

        List<JournalEntry> filteredByEverything = controller.filterEntries(7, substring, date, category);
        showFilteredEntries(filteredByEverything);
    }
    private static void exportEntries() {
        controller.exportEntries();
    }

    public static void showOptions() {
        System.out.println("Selecione uma das opções abaixo:");
        System.out.println("1. Nova entrada");
        System.out.println("2. Mostrar novas entradas");
        System.out.println("3. Filtrar entradas");
        System.out.println("4. Exportar entradas");
        System.out.println("5. Sair");
        System.out.print(">> ");
    }

    private static void showCategoryOptions() {
        System.out.println("Selecione uma das categorias abaixo:");
        System.out.println("1. Pessoal");
        System.out.println("2. Social");
        System.out.println("3. Educacional");
        System.out.println("4. Profissional");
        System.out.print(">> ");
    }

    private static void showFilterOptions(){
        System.out.println("Selecione uma das opções de filtragem abaixo:");
        System.out.println("1. Filtrar entradas por Substring");
        System.out.println("2. Filtrar entradas por Data");
        System.out.println("3. Filtrar entradas por Categoria");
        System.out.println("4. Filtrar entradas por Substring e Data");
        System.out.println("5. Filtrar entradas por Substring e Categoria");
        System.out.println("6. Filtrar entradas por Data e Categoria");
        System.out.println("7. Buscar por entrada específica");
        System.out.print(">> ");
    }
    public static String categorySelector(Scanner scan){
        String category = "";
        boolean validCategory = false;

        while (!validCategory) {
            showCategoryOptions();
            int choice = scan.nextInt();
            scan.nextLine();

            switch (choice) {
                case 1:
                    category = "Pessoal";
                    validCategory = true;
                    break;
                case 2:
                    category = "Social";
                    validCategory = true;
                    break;
                case 3:
                    category = "Educacional";
                    validCategory = true;
                    break;
                case 4:
                    category = "Profissional";
                    validCategory = true;
                    break;
                default:
                    System.out.println("Escolha uma categoria válida!");
            }
        }
        return category;
    }

    private static void showFilteredEntries(List<JournalEntry> entries) {
        if (entries.isEmpty()) {
            System.out.println("Nenhuma entrada encontrada com os filtros escolhidos.");
        } else {
            System.out.println("Entradas filtradas:");
            for (JournalEntry entry : entries) {
                System.out.println(entry);
            }
        }
    }
    private static Date parseDate(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            return sdf.parse(dateString);
        } catch (ParseException e) {
            System.out.println("Digite uma data válida no formato dd-MM-aaaa.");
            return null;
        }
    }
}
