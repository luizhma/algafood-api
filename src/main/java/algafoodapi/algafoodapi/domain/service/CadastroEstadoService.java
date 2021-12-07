package algafoodapi.algafoodapi.domain.service;

import algafoodapi.algafoodapi.domain.exception.EntidadeEmUsoException;
import algafoodapi.algafoodapi.domain.exception.EntidadeNaoEncontradaExeception;
import algafoodapi.algafoodapi.domain.exception.EstadoNaoEncontradoException;
import algafoodapi.algafoodapi.domain.model.Estado;
import algafoodapi.algafoodapi.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroEstadoService {

    private static final String MSG_ESTADO_EM_USO = "O estádo %d não pode ser excluído";

    @Autowired
    private EstadoRepository estadoRepository;

    @Transactional
    public Estado salvar(Estado estado){
        return estadoRepository.save(estado);
    }

    @Transactional
    public void remover (Long id){
        try {
            estadoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw new EstadoNaoEncontradoException(id);

            } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_ESTADO_EM_USO, id));
        }
    }

    public Estado buscaOuFalha(Long id){
        return estadoRepository.findById(id)
                .orElseThrow(() -> new EstadoNaoEncontradoException(id));
    }
}
