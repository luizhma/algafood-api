package algafoodapi.algafoodapi.jpa;

import algafoodapi.algafoodapi.domain.model.Restaurante;
import algafoodapi.algafoodapi.domain.repository.RestauranteRepository;
//import algafoodapi.algafoodapi.domain.repository.RestauranteRepositoryQueries;
import algafoodapi.algafoodapi.infraestructure.repository.spec.RestauranteSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
/*
@Repository
public class RestauranteRepositoryImlp implements RestauranteRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Autowired @Lazy
    private RestauranteRepository restauranteRepository;
    @Override
    public List<Restaurante> find(String nome,
                                  BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal){
       /* var jpql = new StringBuilder();
            jpql.append("from Restaurante where 0 = 0 ");

            var parametros = new HashMap<String, Object>();

            if(StringUtils.hasLength(nome)) {
                    jpql.append("and nome like :nome ");
                    parametros.put("nome", "%" + nome + "%");
            }
            if(taxaFreteInicial != null){
                    jpql.append("and taxaFrete >= :taxaIncial");
                    parametros.put("taxaInicial", taxaFreteInicial);
            }
            if(taxaFreteInicial != null){
                    jpql.append("and taxaFrete <= :taxaFinal");
                    parametros.put("taxaFinal", taxaFreteInicial);
            }

        TypedQuery<Restaurante> query = manager.createQuery(jpql.toString(), Restaurante.class);

            parametros.forEach((chave, valor) -> query.setParameter(chave, valor));

            return query.getResultList();


        CriteriaBuilder builder = manager.getCriteriaBuilder();

        CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);
        Root<Restaurante> root = criteria.from(Restaurante.class);

        var predicates = new ArrayList<Predicate>();

        if (StringUtils.hasText(nome)){
            predicates.add(builder.like(root.get("nome"), "%" + nome + "%"));
        }
        if (taxaFreteInicial != null){
            predicates.add(builder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial));
        }
        if (taxaFreteFinal != null){
            predicates.add(builder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal));
        }

        criteria.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Restaurante> query = manager.createQuery(criteria);
        return query.getResultList();
    }

    @Override
    public List<Restaurante> findComFreteGratis(String nome) {
        return restauranteRepository.findAll(RestauranteSpecs.comFrenteGratis()
        .and(RestauranteSpecs.comNomeSemelhante(nome)));
    }
}
            */