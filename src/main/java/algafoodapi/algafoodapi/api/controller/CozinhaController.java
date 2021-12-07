package algafoodapi.algafoodapi.api.controller;

import algafoodapi.algafoodapi.domain.exception.EntidadeEmUsoException;
import algafoodapi.algafoodapi.domain.exception.EntidadeNaoEncontradaExeception;
import algafoodapi.algafoodapi.domain.model.Cozinha;
import algafoodapi.algafoodapi.domain.repository.CozinhaRepository;
import algafoodapi.algafoodapi.domain.service.CadastroCozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinhaService;

    @GetMapping(/*produces = MediaType.APPLICATION_JSON_VALUE*/)
    public List<Cozinha> listar() {
        return cozinhaRepository.findAll();
    }

   /* @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public CozinhasXMLWrapper listaXml() {
        return new CozinhasXMLWrapper(cozinhaRepository.listar());
    }
    */

    //@ResponseStatus(/*value = HttpStatus.OK*/)
    @GetMapping("/{id}"/*produces = MediaType.APPLICATION_JSON_VALUE*/)
    public Cozinha buscar(@PathVariable Long id) {
        return cadastroCozinhaService.buscarOuFalhar(id);

//        Optional<Cozinha> cozinha = cozinhaRepository.findById(id);
//
//        /*HttpHeaders headers = new HttpHeaders();
//        headers.add(HttpHeaders.LOCATION, "http://localhost:8080/cozinhas");
//        //return ResponseEntity.status(HttpStatus.OK).body(cozinha);
//        //return ResponseEntity.status(HttpStatus.OK).build();
//        //return ResponseEntity.ok(cozinha);
//
//        return ResponseEntity.status(HttpStatus.FOUND)
//                .headers(headers)
//                .build();
//        */
//
//        if (cozinha.isPresent()) {
//            return ResponseEntity.ok(cozinha.get());
//        }
//
//        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha salvar(@RequestBody @Valid Cozinha cozinha){
        return cadastroCozinhaService.salvar(cozinha);
    }

    @PutMapping("/{id}")
    public Cozinha atualizar(@PathVariable Long id, @RequestBody @Valid Cozinha cozinha) {
        Cozinha cozinhaAtual = cadastroCozinhaService.buscarOuFalhar(id);

        BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");

        return cadastroCozinhaService.salvar(cozinhaAtual);



    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Cozinha> remover(@PathVariable Long id) {
//        try {
//            cadastroCozinhaService.remover(id);
//            return ResponseEntity.noContent().build();
//
//        } catch (EntidadeEmUsoException e) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).build();
//        }
//    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        cadastroCozinhaService.remover(id);

    }
}
