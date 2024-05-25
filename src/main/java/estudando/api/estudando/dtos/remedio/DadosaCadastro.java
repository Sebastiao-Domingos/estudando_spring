package estudando.api.estudando.dtos.remedio;

import java.math.BigDecimal;
import java.time.LocalDate;

import estudando.api.estudando.constants.LaboratorioEnum;
import estudando.api.estudando.constants.ViaEnum;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosaCadastro( 
     @NotBlank String nome, 
     @Enumerated ViaEnum via ,
      @NotBlank String lote , 
      @NotNull long quantidade  ,
      @Future LocalDate validade , 
      @Enumerated LaboratorioEnum laboratorio,
      @NotNull BigDecimal preco 
      ) {
}
