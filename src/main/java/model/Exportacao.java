package model;

import java.util.ArrayList;
import model.Persistencia;



public class Exportacao {

	private static Exportacao instance;
	
	private Exportacao(){}

	public static Exportacao getInstance() {
		if (instance == null) // 1a vez que chama-se getInstance
		  instance = new Exportacao();
		return instance;
	}
    
	public void exportaObjeto(Modelo obj){

		// cria uma lista e chama o método 'leDados' para popular esta lista
		// com os dados presentes no banco de dados

		Persistencia persiste = new Persistencia();
		
		ArrayList<Modelo> list = persiste.leDados();

		// Adicionando à lista o novo objeto que foi recebido como parâmetro

		list.add(obj);

		// Chamando método que escreve objetos de uma lista no banco de dados

		persiste.armazenaDados(list);
    }
    
}
