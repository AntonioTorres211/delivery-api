package com.delivery_api.Projeto.Delivery.API.repository;

import com.delivery_api.Projeto.Delivery.API.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    // ---- JÁ EXISTENTES ----

    // Busca produtos por restaurante
    List<Produto> findAllByRestauranteId(Long restauranteId);

    // Busca produtos por categoria
    List<Produto> findAllByCategoria(String categoria);

    // Busca por disponibilidade
    List<Produto> findAllByDisponivel(Boolean disponivel);


    // ---- ADICIONADOS PELO ProdutoService ----

    // Listar produtos ativos (soft delete)
    List<Produto> findByAtivoTrue();

    // Buscar por nome (contém, ignorando maiúsculas/minúsculas)
    List<Produto> findByNomeContainingIgnoreCase(String nome);

    // Buscar por categoria ignorando case
    List<Produto> findByCategoriaIgnoreCase(String categoria);

    // Buscar por faixa de preço
    List<Produto> findByPrecoBetween(BigDecimal min, BigDecimal max);
}
