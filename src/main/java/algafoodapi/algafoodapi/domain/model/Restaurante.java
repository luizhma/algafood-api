package algafoodapi.algafoodapi.domain.model;
import algafoodapi.algafoodapi.core.validation.Groups;
import algafoodapi.algafoodapi.core.validation.TaxaFrete;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Restaurante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(length = 30, nullable = false)
    private String nome;

    @PositiveOrZero
    //@TaxaFrete
    @Column(name = "taxa_frete", nullable = false)
    private BigDecimal taxaFrete;


    //@JsonIgnoreProperties("hibernateLazyInitializer")
    @Valid
    @ConvertGroup(from = Default.class, to = Groups.CozinhaId.class)
    @NotNull
    @ManyToOne
    @JoinColumn(name = "cozinha_id")
    private Cozinha cozinha;


    @JsonIgnore
    @Embedded
    private Endereco endereco;


    @JsonIgnore
    @CreationTimestamp
    @Column(columnDefinition = "dateTime")
    private LocalDateTime dataCadastro;

    @JsonIgnore
    @UpdateTimestamp
    @Column(columnDefinition = "dateTime")
    private LocalDateTime dataAtualizacao;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "restaurante_forma_pagamento",
    joinColumns = @JoinColumn(name = "restaurante_id"),
    inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
    private List<FormaPagamento> formasPagamento = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "restaurante")
    private List<Produto> produtos = new ArrayList<>();

}
