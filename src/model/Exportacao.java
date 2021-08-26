package model;

import java.io.FileWriter;
import controller.Entrada;
import controller.Utilitaria;
import view.MenuPrincipal;
import java.util.Date;


public class Exportacao {

    public void exporta_objeto(Entrada obj){
        
        Gson gson = new Gson();

	    // converte objetos Java para JSON e retorna JSON como String
	    String json = gson.toJson(obj);

	    try {
	    	//Escreve Json convertido em arquivo chamado ".json"
            FileWriter writer = new BufferedWriter(new FileWriter("C:\\banco_de_dados/.json", true));
	    	writer.append(json);
	    	writer.close();

	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
    }
    
}
