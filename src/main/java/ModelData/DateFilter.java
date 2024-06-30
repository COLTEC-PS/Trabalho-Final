package ModelData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DateFilter implements JournalEntryFilter {
    private final Date date;

    public DateFilter(Date date) {
        this.date = date;
    }

    @Override
    public List<JournalEntry> filter (List<JournalEntry> entries) {
        List<JournalEntry> filteredEntries = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String filterDateStr = sdf.format(date);

        for (JournalEntry entry : entries) {
            String entryDateStr = sdf.format(entry.getDate());
            if (entryDateStr.equals(filterDateStr)) {
                filteredEntries.add(entry);
            }
        }

        return filteredEntries;
    }
}
