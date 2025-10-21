package br.uniesp.si.techback.service;

import br.uniesp.si.techback.exception.EntidadeNaoEncontradaException;
import br.uniesp.si.techback.model.Filme;
import br.uniesp.si.techback.repository.FilmeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmeService {

    private final FilmeRepository filmeRepository;

    public List<Filme> listar() {
        return filmeRepository.findAll();
    }

c Filme buscarPorId(Long id) {
        return filmeRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Filme n o encontrado com o ID: " + id));
    }


    @Transactional
    public Filme salvar(Filme filme) {
        return filmeRepository.save(filme);
    }


    @Transactional
    public Filme atualizar(Long id, Filme filme) {

        if (!filmeRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Filme n o encontrado com o ID: " + id);
        }


        filme.setId(id);


        return filmeRepository.save(filme);
    }


    @Transactional
    public void excluir(Long id) {

        if (!filmeRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Filme n o encontrado com o ID: " + id);
        }


        filmeRepository.deleteById(id);
    }
}
