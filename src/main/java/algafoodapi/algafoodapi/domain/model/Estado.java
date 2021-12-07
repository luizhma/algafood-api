package algafoodapi.algafoodapi.domain.model;

import algafoodapi.algafoodapi.core.validation.Groups;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Estado {

    @NotNull(groups = Groups.EstadoId.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NotBlank
    private String nome;

}
