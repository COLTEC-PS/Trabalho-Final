package model;

import java.util.ArrayList;
import model.*;


public class FiltragemTexto extends FiltragemDecorator {
    
    public FiltragemTexto(Filtragem f){
        super(f);
    }

    public FiltragemTexto(){
        super();
    }

    public void filtraEntradas(ArrayList<Modelo> entradas, Object filtro){
        super.filtraEntradas(entradas, filtro);
        return;
    }
}
