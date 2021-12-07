package algafoodapi.algafoodapi.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;


public abstract class EntidadeNaoEncontradaExeception extends NegocioException {
    private static final long serialVersionUID = 1L;


    public EntidadeNaoEncontradaExeception(String mensagem) {
        super(mensagem);
    }
}
