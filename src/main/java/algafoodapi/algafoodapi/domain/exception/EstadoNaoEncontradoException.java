package algafoodapi.algafoodapi.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaExeception {
    private static final long serialVersionUID = 1L;


    public EstadoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public EstadoNaoEncontradoException(Long estadoId){
        this(String.format("Não existe cadastro de estado com o código %d", estadoId));
    }
}
