package algafoodapi.algafoodapi.domain.service;

import algafoodapi.algafoodapi.domain.exception.EntidadeNaoEncontradaExeception;
import algafoodapi.algafoodapi.domain.exception.RestauranteNaoEncontradoException;
import algafoodapi.algafoodapi.domain.model.Cozinha;
import algafoodapi.algafoodapi.domain.model.Restaurante;
import algafoodapi.algafoodapi.domain.repository.CozinhaRepository;
import algafoodapi.algafoodapi.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroRestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinhaService;

    @Transactional
    public Restaurante salvar(Restaurante restaurante){
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cadastroCozinhaService.buscarOuFalhar(cozinhaId);
            restaurante.setCozinha(cozinha);
            return restauranteRepository.save(restaurante);
    }

    @Transactional
    public void remover(Long id){
        try {
            restauranteRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new RestauranteNaoEncontradoException(id);
        }
    }

    public Restaurante buscaOuFalha(Long id){
        return restauranteRepository.findById(id)
                .orElseThrow(() -> new RestauranteNaoEncontradoException( id));
    }

}
