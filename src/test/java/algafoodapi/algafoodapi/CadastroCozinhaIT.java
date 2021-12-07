package algafoodapi.algafoodapi;

import algafoodapi.algafoodapi.domain.exception.CozinhaNaoEncontradaException;
import algafoodapi.algafoodapi.domain.exception.EntidadeEmUsoException;
import algafoodapi.algafoodapi.domain.model.Cozinha;
import algafoodapi.algafoodapi.domain.model.Restaurante;
import algafoodapi.algafoodapi.domain.service.CadastroCozinhaService;
import algafoodapi.algafoodapi.domain.service.CadastroRestauranteService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.flywaydb.core.Flyway;
import org.hamcrest.Matchers;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CadastroCozinhaIT {
/***************************TESTES DE INTEGRAÇÃO ***************************/
/*
    @Autowired
    CadastroCozinhaService cadastroCozinhaService;

    @Autowired
    CadastroRestauranteService cadastroRestauranteService;

     @Test
        public void testCreateCozinhaWithSucess() {
         // cenário
            Cozinha cozinha = new Cozinha();
            cozinha.setNome("Chinesa");

            //ação
            cozinha = cadastroCozinhaService.salvar(cozinha);

            //validação
            assertThat(cozinha).isNotNull();
            assertThat(cozinha.getId()).isNotNull();
     }

     @Test(expected = ConstraintViolationException.class)
        public void testCreateCozinhaWithoutNome() {
            Cozinha cozinha = new Cozinha();
            cozinha.setNome(null);

            cozinha = cadastroCozinhaService.salvar(cozinha);

    }

    @Test(expected = CozinhaNaoEncontradaException.class)
    public void testDeleteCozinhaInexistente() {
         cadastroCozinhaService.remover(180L);
    }

    @Test(expected = EntidadeEmUsoException.class)
    public void testDeleteCozinhaEmUso() {
        cadastroCozinhaService.remover(1L);
    }

 */
    /*************************** FIM TESTES DE INTEGRAÇÃO ***************************/

    /*************************** TESTES DE API ***************************/

    @Autowired
    private Flyway flyway;

    @LocalServerPort
    private int port;

    @Before
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/cozinhas";

        flyway.migrate();
    }

    @Test
    public void deveRetornarStatus200WhenConsultarCozinhas() {
        RestAssured.given()
                .accept(ContentType.JSON)
        .when()
                .get()
        .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void deveConter4CozinhasWhenConsultarCozinhas() {
        RestAssured.given()
                    .accept(ContentType.JSON)
                .when()
                    .get()
                .then()
                    .body("", Matchers.hasSize(4))
                    .body("nome", Matchers.hasItems("Indiana", "Tailandeza"));
    }

    @Test
    public void deveRetornarStatus201WhenCadastrarCozinha() {
        RestAssured.given()
                .body("{ \"nome\" : \"chinesa\" }")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
        .when()
                .post()
        .then()
                .statusCode(HttpStatus.CREATED.value());
    }

}


