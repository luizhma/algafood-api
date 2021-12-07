package algafoodapi.algafoodapi.domain.service;

import algafoodapi.algafoodapi.domain.exception.CozinhaNaoEncontradaException;
import algafoodapi.algafoodapi.domain.exception.EntidadeEmUsoException;
import algafoodapi.algafoodapi.domain.exception.EntidadeNaoEncontradaExeception;
import algafoodapi.algafoodapi.domain.model.Cozinha;
import algafoodapi.algafoodapi.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroCozinhaService {


    private static final String MSG_COZINHA_EM_USO =
            "Cozinha de de código %d não pode ser removidade, pois está em uso";

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Transactional
    public Cozinha salvar(Cozinha cozinha) {
        return cozinhaRepository.save(cozinha);
    }

    @Transactional
    public void remover(Long id){
        try {
            cozinhaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new CozinhaNaoEncontradaException(id);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_COZINHA_EM_USO, id));
        }
    }

    public Cozinha buscarOuFalhar(Long id){
        return cozinhaRepository.findById(id)
                .orElseThrow(() -> new CozinhaNaoEncontradaException(id));
    }
}
