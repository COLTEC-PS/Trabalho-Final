package ModelData;

import java.util.ArrayList;
import java.util.List;

public class SubstringFilterDecorator extends FilterDecorator {
    private final String substring;

    public SubstringFilterDecorator(JournalEntryFilter nextFilter, String substring) {
        super(nextFilter);
        this.substring = substring;
    }

    @Override
    public List<JournalEntry> filter (List<JournalEntry> entries) {
        List<JournalEntry> previousFilteredEntries = getFilteredEntries(entries);
        List<JournalEntry> filteredEntries = new ArrayList<>();

        for (JournalEntry entry : previousFilteredEntries) {
            if (entry.getText().toLowerCase().contains(substring.toLowerCase())) {
                filteredEntries.add(entry);
            }
        }

        return filteredEntries;
    }
}
