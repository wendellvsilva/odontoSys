package odonto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import odonto.dto.DadosCadastroDentista;
import odonto.model.Dentista;
import odonto.repository.DentistaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DentistaService {

    @Autowired
    private DentistaRepository dentistaRepository;

    public Dentista cadastrarDentista(DadosCadastroDentista dados) {
        Dentista dentista = new Dentista(dados);
        return dentistaRepository.save(dentista);
    }

    public List<Dentista> listarTodosDentistas() {
        return dentistaRepository.findAll();
    }

    public Dentista buscarDentistaPorId(Long id) {
        Optional<Dentista> dentista = dentistaRepository.findById(id);
        return dentista.orElse(null);
    }
}
