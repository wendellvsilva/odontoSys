package odonto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import odonto.dto.DadosCadastroDentista;
import odonto.model.Dentista;
import odonto.repository.DentistaRepository;

@Service
public class DentistaService {

    @Autowired
    private DentistaRepository dentistaRepository;

    public Dentista cadastrarDentista(DadosCadastroDentista dados) {

        Dentista dentista = new Dentista(dados);
        return dentistaRepository.save(dentista);
    }
}
