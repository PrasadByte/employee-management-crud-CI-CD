package employee.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler1  {
    @ExceptionHandler(exception=Exception.class)
    public ResponseEntity<String>exceptionHandler(Exception ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(exception=RuntimeException.class)
    public ResponseEntity<String>runtimeExceptionHandler(RuntimeException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(exception=NullPointerException.class)
    public ResponseEntity<String>nullPointerExceptionHandler(NullPointerException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(exception=EntityNotFoundException.class)
    public ResponseEntity<String>nullPointerExceptionHandler(EntityNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
