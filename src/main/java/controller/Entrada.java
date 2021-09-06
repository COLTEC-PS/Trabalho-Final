package controller; 

import java.util.ArrayList;
import java.util.Date;
import model.Exportacao;
import model.Modelo;


public class Entrada {      

    Modelo model;

    public Entrada(String dateParametro){
        Date date = Utilitaria.convertStringToDate(dateParametro);  
        this.model = new Modelo(new ArrayList<>(), date, new ArrayList<>());      
    }

    public void addTxt(String word){
        this.model.setPalavraTexto(word);
    }

    public void adicionaCategoria(String word){
        // método que recebe uma palavra do texto da entrada
        // e determina se ela se encaixa em uma das categorias
        // padrão, cuja lista foi determinada na classe
        // Utilitaria 

        ArrayList <String> array = Utilitaria.getCategoriasPadrao();

        for (String s : array){
            if (word.equalsIgnoreCase(s)){
                ArrayList<String> aux = this.model.getCategorias();
                aux.add(word);
                this.model.setCategorias(aux);
            }
        }

    }

    // Método que retorna true se a entrada passa no filtro e retorna false caso contrário

    public Boolean filtraTextoEntrada(String texto){
        Boolean b = false;
        // Vetor de strings que representa todas as palavras do texto do parâmetro
        String[] textoFiltro = texto.split(" ");
        for (String tf : textoFiltro) {  
            for (String s : this.model.getTexto()) {
                int ultimaPosicaoArray = this.model.getTexto().size() - 1;
                if (tf.equals(s)){
                    b = true;
                }
                else if (s.equals(this.model.getTexto().get(ultimaPosicaoArray))){
                    return false;
                }
                
            }
        }
        return b;
    }

    // Método que retorna true se a entrada passa no filtro e retorna false caso contrário

    public Boolean filtraDataEntrada(String data){
        Boolean b;
        Date dataFiltro = Utilitaria.convertStringToDate(data);
        b = (this.model.getDate() == dataFiltro);
        return b;
    }

    // Método que retorna true se a entrada passa no filtro e retorna false caso contrário
    // Determina se a entrada possui uma determinada categoria

    public Boolean filtraCategoriaEntrada(String categoria){
        for (String s : this.model.getCategorias()) {
            if (s.equals(categoria))
                return true;
        }
        return false;
    }

    public void exportarEntrada(){
        Exportacao ex = new Exportacao();
        ex.exportaObjeto(this.model);
    }

}
