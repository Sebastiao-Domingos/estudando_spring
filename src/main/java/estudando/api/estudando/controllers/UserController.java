package estudando.api.estudando.controllers;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import estudando.api.estudando.dtos.UserRecordDTOS;
import estudando.api.estudando.modal.EnderecoModal;
import estudando.api.estudando.modal.UserModal;
import estudando.api.estudando.repositories.UserRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UserController {
    @Autowired
    UserRepository repository ;

    @PostMapping
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<UserModal>  addUser(HttpMessageNotReadableException ex,@RequestBody @Valid UserRecordDTOS dados){
        Throwable cause = ex.getCause();
        System.out.println(dados);
        System.out.println(cause);
        var userModal = new UserModal();
        BeanUtils.copyProperties(dados, userModal);

        for( EnderecoModal endereco : userModal.getEnderecos() ){
            endereco.setUser(userModal);
        }

        var result = repository.save(userModal);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    
    @GetMapping
    public ResponseEntity<List<UserModal>> getUsers(){
        var users = repository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }
}
