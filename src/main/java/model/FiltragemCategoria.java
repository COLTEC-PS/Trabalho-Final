package model;

import java.util.ArrayList;

import controller.*;
import model.*;

public class FiltragemCategoria extends FiltragemDecorator {

    public FiltragemCategoria(Filtragem f){
        super(f);
    }

    public FiltragemCategoria(){
        super();
    }

    
    public void filtraEntradasPorCategoria(ArrayList<Modelo> listaDados, String categoria){
        // Armazenou os dados do banco de dados dentro do array 'listaDados' que é atributo de 'FiltragemDecorator'
        super.filtraEntradas(listaDados, categoria);
        ArrayList<Modelo> auxArray = new ArrayList<>();

        // Chamando o método que filtra por categoria para cada elemento da lista de dados
        
        for (Modelo m : listaDados) {
            if (filtraCategoria(m, categoria)){
                auxArray.add(m);
            }
        }
        atualizaListaDados(auxArray, listaDados);
    }

    // Método que filtra os dados por categoria

    public Boolean filtraCategoria(Modelo m, String categoria){
        for (String s : m.getCategorias()) {
            if (s.equals(categoria))
                return true;
        }
        return false;
    }
}
