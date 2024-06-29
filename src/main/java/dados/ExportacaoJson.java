package dados;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ExportacaoJson implements Exportacao{
    private Gson gson;

    //construtor
    public ExportacaoJson() {
        this.gson = new Gson();
    }

    public void exportar(List<Entrada> entradas, String arquivo){
        try(FileWriter fileWriter = new FileWriter(arquivo)){
            gson.toJson(entradas, arquivo.getClass());
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
