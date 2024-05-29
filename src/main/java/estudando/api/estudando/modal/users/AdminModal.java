
package estudando.api.estudando.modal.users;

import estudando.api.estudando.dtos.users.AdminDtoUpdate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * AdminModal
 */

@Entity(name = "administrador")
@Table(name = "Admin")
@Getter
@Setter
@AllArgsConstructor 
@NoArgsConstructor
public class AdminModal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String email;
    private String palavra_passe;
    private boolean status;


    public void atualizar( AdminDtoUpdate dados){
        if(dados.nome() != null){
            this.setNome(dados.nome());
        }

        if(dados.email() != null){
            this.setEmail(dados.email());
        }

        if(dados.palavra_passe() !=null){
            this.setPalavra_passe(dados.palavra_passe());
        }
    }
}