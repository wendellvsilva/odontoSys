package odonto.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import odonto.dto.DadosCadastroDentista;
import odonto.dto.DadosEndereco;

@Table(name = "dentistas")
@Entity(name = "Dentista")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Dentista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cro;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private DadosEndereco endereco;
    public Dentista(DadosCadastroDentista dados) {
        this.nome = dados.nome();
        this.cro = dados.cro();
        this.especialidade = dados.especialidade();
        this.endereco = new DadosEndereco(
            dados.endereco().logradouro(),
            dados.endereco().bairro(),
            dados.endereco().cep(),
            dados.endereco().cidade(),
            dados.endereco().uf(),
            dados.endereco().complemento(),
            dados.endereco().numero()
        );
    }
}
