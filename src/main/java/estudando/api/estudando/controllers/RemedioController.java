package estudando.api.estudando.controllers;

import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Object>  cadastrar( @RequestBody @Valid DadosaCadastro dados ){
        var remedio = new RemedioModal();
        BeanUtils.copyProperties(dados , remedio);
        repository.save(remedio);
        return ResponseEntity.status(HttpStatus.CREATED).body("created");
    }

    @GetMapping
    public ResponseEntity<List<ListaDadosRemedio>> getremedios(){
        var remedios = repository.findAll().stream().map(ListaDadosRemedio::new).toList();
        return ResponseEntity.status(HttpStatus.OK).body(remedios);
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
}
