package estudando.api.estudando.infra;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class TratamentoDeErros {
    
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> tratar404(){
        return ResponseEntity.notFound().build();
    }   
}
