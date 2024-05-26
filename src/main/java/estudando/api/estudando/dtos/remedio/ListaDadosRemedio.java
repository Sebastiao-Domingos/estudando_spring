package estudando.api.estudando.dtos.remedio;

import estudando.api.estudando.constants.LaboratorioEnum;
import estudando.api.estudando.constants.ViaEnum;
import estudando.api.estudando.modal.RemedioModal;

import java.time.LocalDate;


public record ListaDadosRemedio(
    Long id,
   String nome, 
      ViaEnum via ,
       String lote , 
       long quantidade  ,
       LocalDate validade , 
       LaboratorioEnum laboratorio,
       boolean status

) {
    public ListaDadosRemedio( RemedioModal remedio){
        this(
            remedio.getId(),
            remedio.getNome(), 
            remedio.getVia(), 
            remedio.getLote(), 
            remedio.getQuantidade(), 
            remedio.getValidade(),
             remedio.getLaboratorio(),
             remedio.isStatus()
             );
    }
}
