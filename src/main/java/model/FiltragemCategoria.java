package model;

import java.util.ArrayList;

import model.*;

public class FiltragemCategoria extends FiltragemDecorator {

    public FiltragemCategoria(Filtragem f){
        super(f);
    }

    public FiltragemCategoria(){
        super();
    }
    
    public void filtraEntradas(String categoria){
        // Armazenou os dados do banco de dados dentro do array 'listaDados' que é atributo de 'FiltragemDecorator'
        super.filtraEntradas(categoria);
        for (Modelo m : this.listaDados) {
            
        }
        
        return;
    }

    public Boolean filtraCategoria(Modelo m, String categoria){
    for (String s : m.getCategorias()) {
        if (s.equals(categoria))
            return true;
    }
    return false;
}


}
