package algafoodapi.algafoodapi.infraestructure.repository;

import algafoodapi.algafoodapi.domain.model.Cozinha;
import algafoodapi.algafoodapi.domain.model.Permissao;
import algafoodapi.algafoodapi.repository.PermissaoRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class PermissaoRepositoryImpl implements PermissaoRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Permissao> listar() {
        return manager.createQuery("from Permissao", Permissao.class)
                .getResultList();
    };

    @Override
    public Permissao buscar(Long id) {
        return manager.find(Permissao.class, id);
    };

    @Override
    public Permissao salvar(Permissao permissao) {
        return manager.merge(permissao);
    };

    @Override
    public void remover(Permissao permissao) {
        permissao = buscar(permissao.getId());
        manager.remove(permissao);
    };
}
