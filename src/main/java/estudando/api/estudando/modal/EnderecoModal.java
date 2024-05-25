package estudando.api.estudando.modal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity(name = "enderecos")
@Table(name = "Endereco")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "idEndereco")
public class EnderecoModal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idEndereco;
    private String municipio;
    private String bairro;
    private String rua;

    @ManyToOne
    @JoinColumn(name = "idUser",nullable = false)
    private UserModal user;
}
