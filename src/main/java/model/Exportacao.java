package model;

import java.util.ArrayList;
import model.Persistencia;



public class Exportacao {
	
	public Exportacao(){}

    
	public void exportaObjeto(Modelo obj){

		// cria uma lista e chama o método 'leDados' para popular esta lista
		// com os dados presentes no banco de dados
		
		ArrayList<Modelo> list = new ArrayList<>();
		Persistencia.getInstance().leDados(list);
		

		// Adicionando à lista o novo objeto que foi recebido como parâmetro
		list.add(obj);
		
		// Chamando método que escreve objetos de uma lista no banco de dados

		Persistencia.getInstance().armazenaDados(list);
    }
    
}
