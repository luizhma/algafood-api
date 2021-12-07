package algafoodapi.algafoodapi.domain.exception;

public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaExeception {
    private static final long serialVersionUID = 1L;


    public RestauranteNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public RestauranteNaoEncontradoException(Long restauranteId){
        this(String.format("Não existe cadastro de restaurante com o código %d", restauranteId));
    }
}
