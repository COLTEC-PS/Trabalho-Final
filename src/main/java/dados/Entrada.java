package dados;

import java.util.Date;
import java.util.List;

public class Entrada {
    private String texto;
    private Date data;
    private List<String> categorias;

    //construtor
    public Entrada(String texto, Date data, List<String> categorias) {
        this.texto = texto;
        this.data = data;
        this.categorias = categorias;
    }

    //getters e setters

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public List<String> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<String> categorias) {
        this.categorias = categorias;
    }
}
