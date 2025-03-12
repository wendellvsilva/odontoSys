package odonto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosEndereco(
    @NotBlank(message = "Logradouro não pode ser vazio") String logradouro,
    
    @NotBlank(message = "Bairro não pode ser vazio") String bairro,
    
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP deve estar no formato 12345-678") String cep,
    
    @NotBlank(message = "Cidade não pode ser vazia") String cidade,
    
    @NotBlank(message = "UF não pode ser vazia") String uf,
    
    String complemento, 
    
    @NotBlank(message = "Número não pode ser vazio") String numero
) {}
