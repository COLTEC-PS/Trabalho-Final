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

    public void filtraEntradasPorTexto(ArrayList<Modelo> listaDados, String texto){
        super.filtraEntradas(listaDados, texto);

        ArrayList<Modelo> auxArray = new ArrayList<>();

        for (Modelo m : listaDados) {
            if (filtraTexto(m, texto)){

                auxArray.add(m);
            }
        }
        atualizaListaDados(auxArray, listaDados);
    }

    // Método que retorna true se o objeto passa no filtro e retorna false caso contrário

    public Boolean filtraTexto(Modelo m, String texto){
        Boolean b = false;
        
        // Vetor de strings que representa todas as palavras do texto do parâmetro
        String[] textoFiltro = texto.split(" ");
        String[] textoParametro;

        for (String tf : textoFiltro) {  
            for (String s : m.getTexto()) {
                int ultimaPosicaoArray = m.getTexto().size() - 1;
                if (tf.equals(s)){
                    b = true;
                    break;
                }
                else if (s.equals(m.getTexto().get(ultimaPosicaoArray))){
                    return false;
                }

            }
        }
        return b;
    }
}
