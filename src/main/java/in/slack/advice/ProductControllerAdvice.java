package in.slack.advice;

import in.slack.exception.ProductAlreadyExistsException;
import in.slack.exception.ProductNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ProductControllerAdvice
{
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleProductException(ProductNotFoundException pnf)
    {
        ErrorDetails error = new ErrorDetails(pnf.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(404).body(error);
    }

    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<ErrorDetails> handleProductException(ProductAlreadyExistsException pnf)
    {
        ErrorDetails error = new ErrorDetails(pnf.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(404).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e)
    {

        String error = "Internal Server Error!!";
        return ResponseEntity.status(500).body(error);
    }
}
