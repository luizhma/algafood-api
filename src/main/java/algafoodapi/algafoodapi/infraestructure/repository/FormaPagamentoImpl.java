package algafoodapi.algafoodapi.infraestructure.repository;

import algafoodapi.algafoodapi.domain.model.FormaPagamento;
import algafoodapi.algafoodapi.domain.repository.FormaPagamentoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class FormaPagamentoImpl implements FormaPagamentoRepository {

    @PersistenceContext
    private EntityManager manager;


    @Override
    public List<FormaPagamento> listar() {
        return manager.createQuery("from FormaPagamento", FormaPagamento.class)
                .getResultList();
    };

    @Override
    public FormaPagamento buscar(Long id) {
        return manager.find(FormaPagamento.class, id);
    };

    @Override
    public FormaPagamento salvar(FormaPagamento formaPagamento) {
        return manager.merge(formaPagamento);
    };

    @Override
    public void remover(FormaPagamento formaPagamento) {
        formaPagamento = buscar(formaPagamento.getId());
        manager.remove(formaPagamento);
    };
}
