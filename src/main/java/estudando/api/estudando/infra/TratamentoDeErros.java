package estudando.api.estudando.infra;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class TratamentoDeErros {
    
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> tratar404(){
        return ResponseEntity.notFound().build();
    }   

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> tratador400(MethodArgumentNotValidException ex){
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErros::new).toList());
    }

    public record  DadosErros(String message , String field) {
        public DadosErros(FieldError error){
            this(error.getDefaultMessage() , error.getField());
        }
    }
}
