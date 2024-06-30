package ModelData;

import java.util.ArrayList;
import java.util.List;

public class SubstringFilter implements JournalEntryFilter {
    private final String substring;

    public SubstringFilter(String substring) {
        this.substring = substring;
    }

    @Override
    public List<JournalEntry> filter(List<JournalEntry> entries) {
        List<JournalEntry> filteredEntries = new ArrayList<>();

        for (JournalEntry entry : entries) {
            if (entry.getText().toLowerCase().contains(substring.toLowerCase())) {
                filteredEntries.add(entry);
            }
        }

        return filteredEntries;
    }
}
