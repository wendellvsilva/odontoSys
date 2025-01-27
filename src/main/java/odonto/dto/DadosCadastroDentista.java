package odonto.dto;

import odonto.model.Especialidade;


public record DadosCadastroDentista(String nome, String cro, Especialidade especialidade, DadosEndereco endereco) {
}
