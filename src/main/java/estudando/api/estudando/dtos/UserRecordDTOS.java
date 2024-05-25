package estudando.api.estudando.dtos;

import estudando.api.estudando.modal.EnderecoModal;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRecordDTOS(
    @NotBlank String nome,
    @NotBlank @Email String email,
    @NotBlank String senha,
    @NotBlank EnderecoModal endereco
) {
    
}
