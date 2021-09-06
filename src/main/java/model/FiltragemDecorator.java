package model;

import java.util.ArrayList;

import model.Filtragem;

public class FiltragemDecorator implements Filtragem {
    
    private Filtragem filtragem;

    public ArrayList<Modelo> listaDados;

    public FiltragemDecorator(Filtragem filtragem){
        listaDados = new ArrayList<>();
        this.filtragem = filtragem;
    }

    public FiltragemDecorator(){}

    public void filtraEntradas(Object filtro){
        this.filtragem.filtraEntradas(filtro);       
        Persistencia.getInstance().leDados(this.listaDados);

    }
}
