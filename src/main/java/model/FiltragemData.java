package model;

import java.util.ArrayList;

import model.*;

public class FiltragemData extends FiltragemDecorator {

    public FiltragemData(Filtragem f){
        super(f);
    }

    public FiltragemData(){
        super();
    }
    
    public void filtraEntradas(ArrayList<Modelo> entradas, Object filtro){
        super.filtraEntradas(entradas, filtro);
        return;
    }
}
