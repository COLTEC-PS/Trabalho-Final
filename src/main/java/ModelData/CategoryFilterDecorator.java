package ModelData;

import java.util.ArrayList;
import java.util.List;

public class CategoryFilterDecorator extends FilterDecorator {
    private final String category;

    public CategoryFilterDecorator(JournalEntryFilter nextFilter, String category) {
        super(nextFilter);
        this.category = category;
    }

    @Override
    public List<JournalEntry> filter(List<JournalEntry> entries) {
        List<JournalEntry> previousFilteredEntries = getFilteredEntries(entries);
        List<JournalEntry> filteredEntries = new ArrayList<>();

        for (JournalEntry entry : previousFilteredEntries) {
            if (entry.getCategory().equals(category)) {
                filteredEntries.add(entry);
            }
        }

        return filteredEntries;
    }
}