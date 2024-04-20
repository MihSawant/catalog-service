package sawant.mihir.catalogservice.service;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sawant.mihir.catalogservice.entity.exception.BookAlreadyExistsException;
import sawant.mihir.catalogservice.entity.exception.BookNotFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class BookControllerAdvice {

    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String BookNotFoundHandler(BookNotFoundException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(BookAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    String BookExistsHandler(BookAlreadyExistsException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationErrors(MethodArgumentNotValidException ex){
        var errors = new HashMap<String, String>();
        ex.getBindingResult().getAllErrors()
                .forEach(err -> {
                    var field = ((FieldError) err).getField();
                    var message = err.getDefaultMessage();
                    errors.put(field, message);
                });
        return errors;
    }
}
