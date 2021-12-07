package algafoodapi.algafoodapi.domain.exception;

public class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaExeception {
    private static final long serialVersionUID = 1L;


    public CozinhaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public CozinhaNaoEncontradaException(Long cozinhaId){
        this(String.format("Não existe cadastro de cozinha com o código %d", cozinhaId));
    }
}
