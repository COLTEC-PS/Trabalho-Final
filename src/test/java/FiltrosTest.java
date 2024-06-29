import dados.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FiltrosTest {
    private List<Entrada> testData;

    @BeforeEach
    void setUp() {
        testData = new ArrayList<>();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Entrada entry1 = new Entrada("Texto 1", dateFormat.parse("2023-06-01"), Arrays.asList("Categoria1", "Categoria2"));
            Entrada entry2 = new Entrada("Texto 2", dateFormat.parse("2023-06-02"), Arrays.asList("Categoria1", "Categoria3"));
            Entrada entry3 = new Entrada("Outro texto", dateFormat.parse("2023-06-03"), Arrays.asList("Categoria2", "Categoria3"));

            testData.add(entry1);
            testData.add(entry2);
            testData.add(entry3);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testSubstringFilter() {
        //filtro por substring
        Filtro filter = new FiltroTexto("Texto");

        List<Entrada> filteredEntries = filter.aplicarFiltro(testData);

        assertEquals(2, filteredEntries.size());
    }

    @Test
    void testDateFilter() {
        //filtro por data (de 2023-06-01 a 2023-06-02)
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = dateFormat.parse("2023-06-01");
            Date endDate = dateFormat.parse("2023-06-02");

            Filtro filter = new FiltroData(startDate, endDate);

            List<Entrada> filteredEntries = filter.aplicarFiltro(testData);

            assertEquals(2, filteredEntries.size());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testCategoryFilter() {
        // filtro por categoria
        Filtro filter = new FiltroCategoria("Categoria1");

        List<Entrada> filteredEntries = filter.aplicarFiltro(testData);

        assertEquals(2, filteredEntries.size());
    }

    @Test
    void testSubstringAndDateFilter() {
        // filtro por substring e data (de 2023-06-01 a 2023-06-02)
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = dateFormat.parse("2023-06-01");
            Date endDate = dateFormat.parse("2023-06-02");

            FiltrosCompostos compositeFilter = new FiltrosCompostos();
            compositeFilter.addFilter(new FiltroTexto("Texto"));
            compositeFilter.addFilter(new FiltroData(startDate, endDate));

            List<Entrada> filteredEntries = compositeFilter.aplicarFiltro(testData);

            assertEquals(1, filteredEntries.size());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testSubstringAndCategoryFilter() {
        //filtro por substring e categoria
        FiltrosCompostos compositeFilter = new FiltrosCompostos();
        compositeFilter.addFilter(new FiltroTexto("Texto"));
        compositeFilter.addFilter(new FiltroCategoria("Categoria1"));

        List<Entrada> filteredEntries = compositeFilter.aplicarFiltro(testData);

        assertEquals(1, filteredEntries.size());
    }

    @Test
    void testDateAndCategoryFilter() {
        //filtro por data (de 2023-06-01 a 2023-06-02) e categoria
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = dateFormat.parse("2023-06-01");
            Date endDate = dateFormat.parse("2023-06-02");

            FiltrosCompostos compositeFilter = new FiltrosCompostos();
            compositeFilter.addFilter(new FiltroData(startDate, endDate));
            compositeFilter.addFilter(new FiltroCategoria("Categoria1"));

            List<Entrada> filteredEntries = compositeFilter.aplicarFiltro(testData);

            assertEquals(1, filteredEntries.size());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testSubstringDateAndCategoryFilter() {
        //filtro por substring, data (de 2023-06-01 a 2023-06-02) e categoria
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = dateFormat.parse("2023-06-01");
            Date endDate = dateFormat.parse("2023-06-02");

            FiltrosCompostos compositeFilter = new FiltrosCompostos();
            compositeFilter.addFilter(new FiltroTexto("Texto"));
            compositeFilter.addFilter(new FiltroData(startDate, endDate));
            compositeFilter.addFilter(new FiltroCategoria("Categoria1"));

            List<Entrada> filteredEntries = compositeFilter.aplicarFiltro(testData);

            assertEquals(1, filteredEntries.size());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
