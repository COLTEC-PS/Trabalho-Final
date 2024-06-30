package ModelData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class EntryRepository {
    private static EntryRepository instance;
    private static final String FILE_PATH = "src/main/java/ModelData/entries.json";
    private final Gson gson;

    private EntryRepository() {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public static EntryRepository getInstance() {
        if (instance == null) {
            instance = new EntryRepository();
        }
        return instance;
    }

    public void exportEntries(List<JournalEntry> newEntries) {
        List<JournalEntry> savedEntries = importEntries();

        if (savedEntries == null) {
            savedEntries = new ArrayList<>();
        }

        try (Writer writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            savedEntries.addAll(newEntries);
            gson.toJson(savedEntries, writer);
            System.out.println("Entradas exportadas com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao exportar entradas: " + e.getMessage());
        }
    }
    public List<JournalEntry> importEntries() {
        try (Reader reader = new FileReader(FILE_PATH)) {
            Type entryListType = new TypeToken<List<JournalEntry>>() {}.getType();
            List<JournalEntry> entries = gson.fromJson(reader, entryListType);
            if (entries.isEmpty()){
                System.out.println("Nenhuma entrada encontrada.");
            }
            return entries;
        } catch (IOException e) {
            System.out.println("Nenhum arquivo encontrado no caminho: " + FILE_PATH +". Ele será criado quando alguma entrada for exportada.");
            return null;
        }
    }
}