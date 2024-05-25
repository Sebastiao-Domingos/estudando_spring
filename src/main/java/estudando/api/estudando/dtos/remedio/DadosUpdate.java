package estudando.api.estudando.dtos.remedio;

import java.math.BigDecimal;
import java.time.LocalDate;

import estudando.api.estudando.constants.LaboratorioEnum;
import estudando.api.estudando.constants.ViaEnum;
import jakarta.validation.constraints.Future;

/**
 * DadosUpdate
 */
public record DadosUpdate(
      String nome, 
      ViaEnum via ,
        String lote, 
      long quantidade  ,
      @Future LocalDate validade, 
      LaboratorioEnum laboratorio,
      BigDecimal preco 
) {

}