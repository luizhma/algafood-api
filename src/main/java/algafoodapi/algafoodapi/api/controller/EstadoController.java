package algafoodapi.algafoodapi.api.controller;

import algafoodapi.algafoodapi.domain.exception.EntidadeEmUsoException;
import algafoodapi.algafoodapi.domain.exception.EntidadeNaoEncontradaExeception;
import algafoodapi.algafoodapi.domain.model.Estado;
import algafoodapi.algafoodapi.domain.repository.EstadoRepository;
import algafoodapi.algafoodapi.domain.service.CadastroEstadoService;
import antlr.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estados")
public class EstadoController {
    
    @Autowired
    private EstadoRepository estadoRepository;
    
    @Autowired 
    private CadastroEstadoService cadastroEstadoService;
    
    @GetMapping
    public List<Estado> listar(){
        return estadoRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public Estado buscar(@PathVariable Long id){
        return cadastroEstadoService.buscaOuFalha(id);
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estado salvar(@RequestBody @Valid Estado estado){
        return cadastroEstadoService.salvar(estado);
    }

    @PutMapping("/{id}")
    public Estado atualizar(@PathVariable Long id, @RequestBody @Valid Estado estado){
        Estado estadoAtual = cadastroEstadoService.buscaOuFalha(id);
                BeanUtils.copyProperties(estado, estadoAtual, "id");
                return cadastroEstadoService.salvar(estadoAtual);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id){
        cadastroEstadoService.remover(id);
    }
        
}
