package dados;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

//classe que lida com a persistência dos dados
public class Armazenamento {
    private static Armazenamento instance;
    private List<Entrada> entradasDiario;
    private Gson gson;
    private static final String ARQUIVO = "diario.json";

    private Armazenamento(){
        gson = new Gson();
        entradasDiario = carregarEntradas();
    }

    //implementando o padrão singleton
    public static Armazenamento getInstance(){
        if(instance == null){
            instance = new Armazenamento();
        }
        return instance;
    }

    //get de entradas no diário
    public List<Entrada> getEntradasDiario() {
        return entradasDiario;
    }

    private List<Entrada> carregarEntradas(){
        try(FileReader fileReader = new FileReader(ARQUIVO)){
            Type lista = new TypeToken<ArrayList<Entrada>>(){}.getType();
            return gson.fromJson(fileReader, lista);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private void salvarEntradas() throws IOException {
        try(FileWriter fileWriter = new FileWriter(ARQUIVO)){
            gson.toJson(entradasDiario, fileWriter);
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public void adicionarEntrada(Entrada entrada) throws IOException {
        entradasDiario.add(entrada);
        salvarEntradas();
    }
}
