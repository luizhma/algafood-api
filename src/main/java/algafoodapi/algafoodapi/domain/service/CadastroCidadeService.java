package algafoodapi.algafoodapi.domain.service;

import algafoodapi.algafoodapi.domain.exception.CidadeNaoEncontradaException;
import algafoodapi.algafoodapi.domain.exception.EntidadeEmUsoException;
import algafoodapi.algafoodapi.domain.exception.EntidadeNaoEncontradaExeception;
import algafoodapi.algafoodapi.domain.model.Cidade;
import algafoodapi.algafoodapi.domain.model.Cozinha;
import algafoodapi.algafoodapi.domain.model.Estado;
import algafoodapi.algafoodapi.domain.repository.CidadeRepository;
import algafoodapi.algafoodapi.domain.repository.CozinhaRepository;
import algafoodapi.algafoodapi.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CadastroCidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CadastroEstadoService cadastroEstadoService;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Transactional
    public Cidade salvar(Cidade cidade){
        Long id = cidade.getEstado().getId();
        Estado estado = cadastroEstadoService.buscaOuFalha(id);
        cidade.setEstado(estado);
        return cidadeRepository.save(cidade);
    }

    @Transactional
    public void remover(Long id) {
        try {
            cidadeRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new CidadeNaoEncontradaException(id);
        }catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(
                    String.format("Cidade em uso, código %d", id));
        }
    }

    public Cidade buscaOuFalha(Long id){
        return cidadeRepository.findById(id)
                .orElseThrow(() -> new CidadeNaoEncontradaException(id));
    }

}
