package com.delivery_api.Projeto.Delivery.API.repository;

import com.delivery_api.Projeto.Delivery.API.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    // Busca produtos por restaurante
    List<Produto> findAllByRestauranteId(Long restauranteId);

    // Busca produtos por categoria
    List<Produto> findAllByCategoria(String categoria);

    // Busca por disponibilidade
    List<Produto> findAllByDisponivel(Boolean disponivel);
}
