package estudando.api.estudando.dtos.users;

import jakarta.validation.constraints.Email;

public record AdminDtoUpdate(
    String nome,
    @Email String email,
    String palavra_passe
) {

    
}
