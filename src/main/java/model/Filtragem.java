package model;

import java.util.ArrayList;
import model.Modelo;

public interface Filtragem {

    public void filtraEntradas(ArrayList<Modelo> listaDados, Object filtro);
    public void filtraEntradasPorTexto(ArrayList<Modelo> listaDados, String texto);
    public void filtraEntradasPorData(ArrayList<Modelo> listaDados, String texto);
    public void filtraEntradasPorCategoria(ArrayList<Modelo> listaDados, String texto);

    
}
