package model;

import java.util.ArrayList;

import model.Filtragem;

public class FiltragemDecorator implements Filtragem {
    
    private Filtragem filtragem;


    public FiltragemDecorator(Filtragem filtragem){
        this.filtragem = filtragem;
    }

    public FiltragemDecorator(){}

    public void filtraEntradas(ArrayList<Modelo> listaDados, Object filtro){
        this.filtragem.filtraEntradas(listaDados, filtro);       
        Persistencia.getInstance().leDados(listaDados);
    }

    public void filtraEntradasPorTexto(ArrayList<Modelo> listaDados, String texto){
        this.filtragem.filtraEntradasPorTexto(listaDados, texto);
    }

    public void filtraEntradasPorData(ArrayList<Modelo> listaDados, String texto){
        this.filtragem.filtraEntradasPorData(listaDados, texto);
    }

    public void filtraEntradasPorCategoria(ArrayList<Modelo> listaDados, String texto){
        this.filtragem.filtraEntradasPorCategoria(listaDados, texto);
    }

    // Função auxiliar que atualiza a lista com os dados de outra lista recebida como parâmetro
    public void atualizaListaDados(ArrayList <Modelo> auxArray, ArrayList<Modelo> listaDados){
        listaDados.clear();
        for (Modelo m : auxArray) {
            listaDados.add(m);
        }
    }
}
