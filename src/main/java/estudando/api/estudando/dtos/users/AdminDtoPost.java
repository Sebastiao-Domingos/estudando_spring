package estudando.api.estudando.dtos.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AdminDtoPost(
    @NotBlank String nome,
    @Email String email,
    @NotBlank String palavra_passe
) {
    
}
