package com.delivery_api.Projeto.Delivery.API.service;

import com.delivery_api.Projeto.Delivery.API.entity.Restaurante;
import com.delivery_api.Projeto.Delivery.API.repository.RestauranteRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RestauranteService {
    @Autowired
    private RestauranteRepository restaurenteRepository;

    //CADASTRAR RESTAURANTE
    public Restaurante cadastrar(Restaurante restaurante) {
        // Validar email único
        if (restaurenteRepository.existsByNome(restaurante.getNome())) {
            throw new IllegalArgumentException("Nome já cadastrado: " + restaurante.getNome());
        }

        // Validações de negócio
        validarDadosRestaurante(restaurante);

        // Definir como ativo por padrão
        restaurante.setAtivo(true);

        return restaurenteRepository.save(restaurante);
    }

    //Lista todos os restaurantes ativos
    @Transactional(readOnly = true)
    public List<Restaurante> listarAtivos() {
        return restaurenteRepository.findByAtivoTrue();
    }

    //Buscar restaurante por ID

    @Transactional(readOnly = true)
    public Optional<Restaurante> buscarPorId(Long id) {
        return restaurenteRepository.findById(id);
    }

    public void inativar(Long id) {
        Restaurante restaurante = buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Restaurante não encontrado: " + id));

        restaurante.inativar();
        restaurenteRepository.save(restaurante);
    }

    public void deletar(Long id) {
        Restaurante restaurante = buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Restaurante não encontrado: " + id));

        restaurenteRepository.delete(restaurante);
    }


    private void validarDadosRestaurante(Restaurante restaurante) {
        if (restaurante.getNome() == null || restaurante.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }

        if (restaurante.getCategoria() == null || restaurante.getCategoria().trim().isEmpty()) {
            throw new IllegalArgumentException("Categoria é obrigatoria");
        }

        if (restaurante.getTelefone() == null || restaurante.getTelefone().trim().isEmpty()) {
            throw new IllegalArgumentException("Telefone é obrigatoria");
        }

        if (restaurante.getNome().length() < 2) {
            throw new IllegalArgumentException("Nome deve ter pelo menos 2 caracteres");
        }
    }
}
