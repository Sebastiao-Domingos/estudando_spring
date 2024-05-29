package estudando.api.estudando.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import estudando.api.estudando.dtos.remedio.DadosUpdate;
import estudando.api.estudando.dtos.remedio.DadosaCadastro;
import estudando.api.estudando.dtos.remedio.ListaDadosRemedio;
import estudando.api.estudando.modal.RemedioModal;
import estudando.api.estudando.repositories.RemedioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/remedios")
public class RemedioController {
    @Autowired
    private RemedioRepository repository;
    
    @PostMapping
    @Transactional
    public ResponseEntity<Object>  cadastrar( @RequestBody @Valid DadosaCadastro dados, UriComponentsBuilder uriBuilder ){
        var remedio = new RemedioModal();
        BeanUtils.copyProperties(dados , remedio);
        repository.save(remedio);
        var uri = uriBuilder.path("/remedios/{id}").buildAndExpand(remedio.getId()).toUri(); 
        return ResponseEntity.created(uri).body(new ListaDadosRemedio(remedio));
    }

    @GetMapping
    public ResponseEntity<List<ListaDadosRemedio>> getremedios(@Param(value = "status") boolean status){
        
        var remedios = repository.findByStatus(status).stream().map(ListaDadosRemedio::new).toList();

        return ResponseEntity.ok(remedios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneRemedio(@PathVariable(name = "id") long id){
        Optional<RemedioModal> remeOptional = repository.findById(id);

        if( remeOptional.isEmpty() ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        }

        var remedio = remeOptional.stream().map(ListaDadosRemedio::new).toList();
        return ResponseEntity.status(HttpStatus.OK).body(remedio.get(0));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> updateRemedio(@PathVariable(name = "id") long id , @RequestBody @Valid DadosUpdate dados){
        Optional<RemedioModal> remedio = repository.findById(id);

        if(remedio.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Remedio nao encontrado");
        }

        var remedioRecebido = remedio.get();
        remedioRecebido.atualizar(dados);
        var remedioDtos = remedio.stream().map(ListaDadosRemedio::new).toList();
        return ResponseEntity.status(HttpStatus.OK).body(remedioDtos.get(0));
    }

    @PutMapping("/reativar/{id}")
    @Transactional
    public ResponseEntity<Object> reativar(@PathVariable long id){
        Optional<RemedioModal> remedioOptional = repository.findById(id);

        if(remedioOptional.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
        
        var remedio = remedioOptional.get();
        remedio.reativar();

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> deleteRemedio( @PathVariable(name = "id") long id){
        Optional<RemedioModal> remedio = repository.findById(id);

        if(remedio.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Remedio nao encontrado");
        }
        repository.deleteById(remedio.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body("okay");
    }

    @DeleteMapping("/inativar/{id}")
    @Transactional
    public ResponseEntity<Object> desativando(@PathVariable(name = "id") long id){
        Optional<RemedioModal> remedio = repository.findById(id);

        if(remedio.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        }

        var remedioModal = remedio.get();
        remedioModal.inativar();
        
        return ResponseEntity.noContent().build();
    }
}
