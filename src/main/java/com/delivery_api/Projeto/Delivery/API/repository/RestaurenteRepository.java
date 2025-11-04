package com.delivery_api.Projeto.Delivery.API.repository;
//Buscar por nome, categoria, ativos, ordenação por avaliação

import com.delivery_api.Projeto.Delivery.API.entity.Cliente;
import com.delivery_api.Projeto.Delivery.API.entity.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurenteRepository extends JpaRepository<Restaurante, Long> {
    //Busca restaurante por nome
    Optional<Restaurante> findByNome(String nome);

    //Busca restaurantes por categoria
    List<Restaurante> findAllByCategoria(String categoria);

    //Busca restaurantes ativos
    List<Restaurante> findByAtivoTrue();

    //Busca por restaurantes e ordena por avaliação
    List<Restaurante> findAllByOrderByAvaliacaoAsc();
}
