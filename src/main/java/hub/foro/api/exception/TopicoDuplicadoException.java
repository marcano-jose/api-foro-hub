package hub.foro.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class TopicoDuplicadoException extends RuntimeException {

    public TopicoDuplicadoException(String message) {
        super(message);
    }
}
