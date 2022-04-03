package peaksoft.finalprojectrestapi.exception;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import peaksoft.finalprojectrestapi.model.Response;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(BadRequestException.class)
//    @ResponseStatus(BAD_REQUEST)
//    public Response handlerBadRequestException(BadRequestException badRequestException){
//    return Response.builder().httpStatus(BAD_REQUEST)
//            .message(badRequestException.getMessage())
//            .build();
 //   }
}
