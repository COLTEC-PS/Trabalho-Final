package ModelData;

import java.util.ArrayList;
import java.util.List;

public class CategoryFilter implements JournalEntryFilter {
    private final String category;

    public CategoryFilter(String category) {
        this.category = category;
    }

    @Override
    public List<JournalEntry> filter (List<JournalEntry> entries) {
        List<JournalEntry> filteredEntries = new ArrayList<>();

        for (JournalEntry entry : entries) {
            if (entry.getCategory().equalsIgnoreCase(category)) {
                filteredEntries.add(entry);
            }
        }

        return filteredEntries;
    }
}
