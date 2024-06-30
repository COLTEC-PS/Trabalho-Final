import ModelData.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilterTest {

    List<JournalEntry> testEntries;
    @BeforeEach
    void init() {
        testEntries = new ArrayList<>();
        JournalEntry testSubstring = new JournalEntry("Teste de filtragem por substring", "Social");
        JournalEntry testCategory = new JournalEntry("Teste de filtragem por categoria","Educacional");
        testEntries.add(testSubstring);
        testEntries.add(testCategory);
    }

    @Test
    public void testSubstringFilter() {
        SubstringFilter filter = new SubstringFilter("sub");
        List<JournalEntry> filteredEntries = filter.filter(testEntries);

        assertEquals(1, filteredEntries.size());
        assertEquals("Teste de filtragem por substring", filteredEntries.get(0).getText());
    }

    @Test
    public void testCategoryFilter() {
        CategoryFilter filter = new CategoryFilter("Educacional");
        List<JournalEntry> filteredEntries = filter.filter(testEntries);

        assertEquals(1, filteredEntries.size());
        assertEquals("Educacional", filteredEntries.get(0).getCategory());
    }

    @Test
    public void testDateFilter() {
        Date today = new Date();  // Tive que fazer essa gambiarra, pois os objetos de entrada já são criados com a data atual
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        DateFilter filter = new DateFilter(today);

        List<JournalEntry> filteredEntries = filter.filter(testEntries);

        assertEquals(2, filteredEntries.size());
        assertEquals(sdf.format(today), sdf.format(filteredEntries.get(0).getDate()));
    }

    @Test
    public void testSubstringDecorator() {
        SubstringFilterDecorator decorator = new SubstringFilterDecorator(new CategoryFilter("Educacional"), "cat");
        List<JournalEntry> filteredEntries = decorator.filter(testEntries);

        assertEquals(1, filteredEntries.size());
        assertEquals("Teste de filtragem por categoria", filteredEntries.get(0).getText());
    }
    @Test
    public void testDateDecorator() {
        Date today = new Date();  // Tive que fazer essa gambiarra, pois os objetos de entrada já são criados com a data atual
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.format(today);

        DateFilterDecorator decorator = new DateFilterDecorator((new CategoryFilter("Educacional")), today);
        List<JournalEntry> filteredEntries = decorator.filter(testEntries);

        assertEquals(1, filteredEntries.size());
        assertEquals("Teste de filtragem por categoria", filteredEntries.get(0).getText());
    }

    @Test
    public void testCategoryDecorator() {
        CategoryFilterDecorator decorator = new CategoryFilterDecorator((new SubstringFilter("sub")),"Social");
        List<JournalEntry> filteredEntries = decorator.filter(testEntries);

        assertEquals(1, filteredEntries.size());
        assertEquals("Teste de filtragem por substring", filteredEntries.get(0).getText());
    }
    @Test
    public void testEveryFilter() {
        Date today = new Date();  // Tive que fazer essa gambiarra, pois os objetos de entrada já são criados com a data atual
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.format(today);

        FilterDecorator decorator = new CategoryFilterDecorator(new SubstringFilterDecorator(new DateFilter(today),"sub"),"Social");
        List<JournalEntry> filteredEntries = decorator.filter(testEntries);

        assertEquals(1, filteredEntries.size());
        assertEquals("Teste de filtragem por substring", filteredEntries.get(0).getText());
    }
}
