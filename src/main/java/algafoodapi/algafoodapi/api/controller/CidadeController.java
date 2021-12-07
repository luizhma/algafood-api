package algafoodapi.algafoodapi.api.controller;

import algafoodapi.algafoodapi.domain.exception.EntidadeNaoEncontradaExeception;
import algafoodapi.algafoodapi.domain.exception.EstadoNaoEncontradoException;
import algafoodapi.algafoodapi.domain.exception.NegocioException;
import algafoodapi.algafoodapi.domain.model.Cidade;
import algafoodapi.algafoodapi.domain.repository.CidadeRepository;
import algafoodapi.algafoodapi.domain.service.CadastroCidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CadastroCidadeService cadastroCidadeService;;

    @GetMapping
    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Cidade buscar(@PathVariable Long id) {
        return cadastroCidadeService.buscaOuFalha(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cidade salvar(@RequestBody @Valid Cidade cidade){
        try {
            return cadastroCidadeService.salvar(cidade);
        }catch (EstadoNaoEncontradoException e){
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("/{id}")
    public Cidade atualizar(@PathVariable Long id, @RequestBody @Valid Cidade cidade) {
           try {
               Cidade cidadeAtual = cadastroCidadeService.buscaOuFalha(id);
               BeanUtils.copyProperties(cidade, cidadeAtual, "id");
               return cadastroCidadeService.salvar(cidadeAtual);
           }catch (EstadoNaoEncontradoException e){
               throw new NegocioException(e.getMessage(), e);
           }

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover (@PathVariable Long id) {
        cadastroCidadeService.remover(id);
    }


}
