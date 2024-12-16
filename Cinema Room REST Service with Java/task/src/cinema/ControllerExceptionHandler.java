package cinema;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TicketAlreadyPurchasedException.class)
    public ResponseEntity<ErrorMessage> handleTicketAlreadyBooked(TicketAlreadyPurchasedException ex, WebRequest request){
        var error = new ErrorMessage(ex.getMessage());
        return new  ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TokenNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleTokenNotFound(TokenNotFoundException ex, WebRequest request){
        var error = new ErrorMessage(ex.getMessage());
        return new  ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<ErrorMessage> handleWrongPassword(WrongPasswordException ex, WebRequest request){
        var error = new ErrorMessage(ex.getMessage());
        return new  ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        var error = new ErrorMessage(ex.getFieldError().getDefaultMessage());
        return new ResponseEntity<>(error,headers,status);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        System.out.println("executed");
        var req = ((ServletWebRequest) request).getRequest();
        var uri = req.getRequestURI();
        System.out.println("url is " + uri);

        if (uri.equals("/stats")) {
           var error = new ErrorMessage("The password is wrong!");
            return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
        }
        return super.handleMissingServletRequestParameter(ex, headers, status, request);
    }
}
