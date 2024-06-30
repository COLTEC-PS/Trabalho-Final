package Controller;

import ModelData.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JournalController {
    private final List<JournalEntry> entries;
    private final EntryRepository repository;

    public JournalController() {
        this.entries = new ArrayList<>();
        this.repository = EntryRepository.getInstance();
    }
    public void addEntry(String entryText, String category) {
        JournalEntry newEntry = new JournalEntry(entryText, category);
        entries.add(newEntry);
    }
    public List<JournalEntry> getEntries() {
        return entries;
    }

    public List<JournalEntry> filterEntries(int option, String substring, Date date, String category) {
        JournalEntryFilter filter = null;
        List<JournalEntry> filteredEntries = new ArrayList<>();

        switch (option) {
            case 1:
                filter = new SubstringFilter(substring);
                break;
            case 2:
                filter = new DateFilter(date);
                break;
            case 3:
                filter = new CategoryFilter(category);
                break;
            case 4:
                filter = new SubstringFilterDecorator(new DateFilter(date), substring);
                break;
            case 5:
                filter = new SubstringFilterDecorator(new CategoryFilter(category), substring);
                break;
            case 6:
                filter = new DateFilterDecorator(new CategoryFilter(category), date);
                break;
            case 7:
                filter = new CategoryFilterDecorator(new DateFilterDecorator(new SubstringFilter(substring), date), category);
                break;
            default:
                System.out.println("Escolha um filtro válido");
        }

        if (filter == null) {
            return new ArrayList<>();
        }

        List<JournalEntry> allEntries = repository.importEntries();

        if (allEntries != null) {
            filteredEntries = filter.filter(allEntries);
        }

        return filteredEntries;
    }

    public void exportEntries() {
        if (!entries.isEmpty()){
            repository.exportEntries(entries);
            entries.clear();
        } else {
            System.out.println("Nenhuma nova entrada encontrada!");
        }
    }

}
