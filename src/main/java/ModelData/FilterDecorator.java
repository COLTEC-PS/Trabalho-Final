package ModelData;

import java.util.ArrayList;
import java.util.List;

public abstract class FilterDecorator implements JournalEntryFilter {
    protected final JournalEntryFilter decoratedFilter;

    public FilterDecorator(JournalEntryFilter decoratedFilter) {
        this.decoratedFilter = decoratedFilter;
    }

    protected List<JournalEntry> getFilteredEntries(List<JournalEntry> entries) {
        List<JournalEntry> filteredEntries;
        if (decoratedFilter != null) {
            filteredEntries = decoratedFilter.filter(entries);
        } else {
            filteredEntries = new ArrayList<>(entries);
        }
        return new ArrayList<>(filteredEntries);
    }

    @Override
    public abstract List<JournalEntry> filter(List<JournalEntry> entries);
}