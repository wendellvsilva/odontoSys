package odonto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import jakarta.validation.constraints.Size;
import odonto.model.Especialidade;

public record DadosCadastroDentista(
    @NotBlank(message = "Nome não pode ser vazio") String nome,
    
    @NotBlank(message = "CRO não pode ser vazio")
    @Size(min = 4, max = 10, message = "CRO deve ter entre 4 e 10 caracteres") 
    String cro,
    
    @NotNull(message = "Especialidade é obrigatória") Especialidade especialidade, 
    
    @NotNull(message = "Endereço é obrigatório") DadosEndereco endereco
) {}
