package algafoodapi.algafoodapi.domain.model;

import algafoodapi.algafoodapi.core.validation.Groups;
import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

@Data
@Entity
public class Cidade {

    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NotBlank
    private String nome;

    @Valid
    @ConvertGroup(from = Default.class, to = Groups.EstadoId.class)
    @NotNull
    @ManyToOne
    @JoinColumn(name = "estado_id", nullable = false)
    private Estado estado;

}
