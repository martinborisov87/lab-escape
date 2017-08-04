package co.tide.lab.labescape.api;

import co.tide.lab.labescape.exception.InvalidLabyrinthException;
import co.tide.lab.labescape.exception.InvalidStartingPositionException;
import co.tide.lab.labescape.exception.NoEscapeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(NoEscapeException.class)
    @ResponseBody
    public ResponseEntity<String> noEscapeException(NoEscapeException e) {
        log.info("NoEscapeException raised");
        return new ResponseEntity<>("No escape found!", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({InvalidStartingPositionException.class, InvalidLabyrinthException.class, IllegalArgumentException.class})
    @ResponseBody
    public ResponseEntity<String> invalidStartingPositionException(RuntimeException e) {
        return new ResponseEntity<>("Invalid request!", HttpStatus.BAD_REQUEST);
    }
}