package estudando.api.estudando.dtos;

import jakarta.validation.constraints.NotBlank;

public record EnderecoRecordDTOS(
     @NotBlank String municipio,
    @NotBlank String bairro,
    @NotBlank String rua
) {
    
}
