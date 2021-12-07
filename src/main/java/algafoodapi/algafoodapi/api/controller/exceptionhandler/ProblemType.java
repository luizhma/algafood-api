package algafoodapi.algafoodapi.api.controller.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada"),
    ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
    REQUISICAO_INCORRETA("/requisicao-incorreta", "Requisicao-incorreta"),
    MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensivel"),
    PARAMETRO_INVALIDO("/paramentro-invalido", "Parametro invalido"),
    RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
    ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema"),
    DADOS_INVALIDOS("/dados-invalidos", "Dados inválidos");

    private String title;
    private String uri;

    ProblemType(String path, String title){
        this.uri = "https://algafood.com.br" + path;
        this.title = title;
    }
}
