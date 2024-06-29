package dados;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class FiltroData implements Filtro{
    private Date dataInicio;
    private Date dataFinal;

    public FiltroData(Date dataInicio, Date dataFinal) {
        this.dataInicio = dataInicio;
        this.dataFinal = dataFinal;
    }

    @Override
    public List<Entrada> aplicarFiltro(List<Entrada> entradas) {
        return entradas.stream()
                .filter(entry -> !entry.getData().before(dataInicio) && !entry.getData().after(dataFinal))
                .collect(Collectors.toList());
    }
}
