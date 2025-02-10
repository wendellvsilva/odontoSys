package odonto.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Tratamento {
    
    @Id
    private Long id;
    private String descricao;
    private BigDecimal valor;
    private String duracaoEstimada; //ex: "1 hora" ou "2 sessões"
    private boolean ativo;

}

