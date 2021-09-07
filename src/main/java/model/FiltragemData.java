package model;

import java.util.ArrayList;
import java.util.Date;
import model.*;
import controller.*;

public class FiltragemData extends FiltragemDecorator {

    public FiltragemData(Filtragem f){
        super(f);
    }

    public FiltragemData(){
        super();
    }


    public void filtraEntradasPorData(ArrayList<Modelo> listaDados, String data){

        // Armazenou os dados do banco de dados dentro do array 'listaDados' que é atributo de 'FiltragemDecorator'
        super.filtraEntradas(listaDados, data);
        ArrayList<Modelo> auxArray = new ArrayList<>();

        // Chamando o método que filtra por data para cada elemento da lista de dados
        
        for (Modelo m : listaDados) {
            if (filtraData(m, data)){
                auxArray.add(m);
            }
        }
        atualizaListaDados(auxArray, listaDados);
    }

    // Método que filtra os dados por data

    public Boolean filtraData(Modelo m, String data){
        Boolean b;
        Date dataFiltro = Utilitaria.convertStringToDate(data);
        b = (m.getDate().equals(dataFiltro));
        return b;
    }
}
