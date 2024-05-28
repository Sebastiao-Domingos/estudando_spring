package estudando.api.estudando.modal;

import estudando.api.estudando.constants.LaboratorioEnum;
import estudando.api.estudando.constants.ViaEnum;
import estudando.api.estudando.dtos.remedio.DadosUpdate;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Entity(name = "remedios")
@Table(name = "Remedio")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class RemedioModal {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;

    @Enumerated(EnumType.STRING)
    private ViaEnum via;
    private String lote;
    private long quantidade;
    private LocalDate validade;
    private boolean status;

    @Enumerated(EnumType.STRING)
    private LaboratorioEnum laboratorio;

    public void atualizar(DadosUpdate dados){
        if( dados.nome() !=null){
            this.setNome(dados.nome());
        }
        if(dados.lote() != null){
            this.setLote(dados.lote());
        }
         if(dados.quantidade() > 0){
            this.setQuantidade(dados.quantidade());;
        }
         if(dados.laboratorio() != null){
            this.laboratorio = dados.laboratorio();
        }
        if(dados.via() !=null){
            this.setVia( dados.via());
        }
        if(dados.validade() != null){
            this.setValidade(dados.validade());
        }
    }

    public void inativar(){
        this.status = false;
    }
    public void reativar(){
        this.status =true;
    }

}
