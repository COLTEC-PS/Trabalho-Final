package model;

import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import model.Modelo;
import model.ProtocolFactory;


public class Persistencia {

    private Gson gson;
    
    Persistencia(){
        this.gson = new Gson();
    }

    // Método que lê os dados do banco de dados e coloca em uma lista de objetos do tipo 'Modelo'

    public ArrayList<Modelo> leDados(){

        ArrayList<Modelo> list = new ArrayList<>();

        try {
	        // cria um reader e lê o arquivo com os dados 
	        // para criar uma lista com as entradas 
	        Reader reader = Files.newBufferedReader(Paths.get("src/banco_de_dados/log.json"));

	        Type listType = new TypeToken<ArrayList<Modelo>>(){}.getType();
	        list = gson.fromJson(reader, listType);

	        // close reader
	        reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void armazenaDados(ArrayList<Modelo> list){
        try{
            // Abrindo o arquivo com os dados no modo de escrita 
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/banco_de_dados/log.json"));
            
            // converte objetos Java para JSON e retorna JSON como String
            // e depois escreve cada objeto da lista convertido no arquivo 'log.json'
            
            writer.write("[\n");
            for (Modelo ob : list) {
                String json = gson.toJson(ob, Modelo.class);
                writer.write(json);
                if (ob == list.get(list.size() - 1)){
                    writer.write("\n");
                }
                else {
                    writer.write(",\n");
                }
            }
            writer.write("]");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
