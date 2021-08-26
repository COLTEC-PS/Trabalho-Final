import java.util.Vector;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;


public class Entrada {      

    private ArrayList text; // texto fornecido para entrada do diário
    private Date date; // data de registro da entrada no diário
    private ArrayList<String> categorias = new ArrayList<String>(); // lista de categorias associadas a entrada do diário

    Entrada(String _date){
        this.text = new ArrayList<String>();
        this.date = new Date(Utilitaria.CONVERT_STRING_TO_DATE(_date));
        
    }

    public void add_word(String word){
        this.text.add(word);

    }

    public void adiciona_categoria(String word){
        // método que recebe uma palavra do texto da entrada e 
        // determina se ela se encaixa em uma das categorias
        // padrão, cuja lista foi determinada na classe
        // Utilitaria 

        for (String s : Utilitaria.CATEGORIAS_PADRAO){
            if (word.equalsIgnoreCase(s)){
                this.categorias.add(word);
            }
        }

    }

    
}
