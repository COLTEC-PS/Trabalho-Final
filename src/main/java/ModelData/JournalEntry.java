package ModelData;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JournalEntry {
    private final String text;
    private final Date date;
    private final String category;

    public JournalEntry(String text, String category) {
        this.text = text;
        this.date = new Date();
        this.category = category;
    }

    public String getText() {
        return text;
    }
    public String getCategory() {
        return category;
    }
    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = sdf.format(date);
        return "Entrada: " + this.getText() + "\n" +
                "Data: " + formattedDate + "\n" +
                "Categoria: " + this.getCategory();
    }

}
