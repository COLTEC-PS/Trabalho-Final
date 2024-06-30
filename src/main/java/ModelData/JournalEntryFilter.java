package ModelData;
import java.util.List;

public interface JournalEntryFilter {
    List<JournalEntry> filter (List<JournalEntry> entries);
}
