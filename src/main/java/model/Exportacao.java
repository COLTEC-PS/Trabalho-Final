package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import controller.Entrada;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;



public class Exportacao {

	private static Exportacao instance;
	
	private Exportacao(){}

	public static Exportacao getInstance() {
		if (instance == null) // 1a vez que chama-se getInstance
		  instance = new Exportacao();
		return instance;
	}
    
	public void exportaObjeto(Modelo obj){

        
        Gson gson = new Gson();

		try {
				
			// cria um reader e lê o arquivo com os dados 
			// para criar uma lista com as entradas 

			Reader reader = Files.newBufferedReader(Paths.get("src/banco_de_dados/log.json"));
			
			Type listType = new TypeToken<ArrayList<Modelo>>(){}.getType();
			ArrayList<Modelo> list = gson.fromJson(reader, listType);
				
			// close reader
			reader.close();
			
			list.add(obj);

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
