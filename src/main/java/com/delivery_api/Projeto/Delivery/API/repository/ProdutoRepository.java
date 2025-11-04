package com.delivery_api.Projeto.Delivery.API.repository;
//Buscar produtos por restaurante, por categoria e disponibilidade

import com.delivery_api.Projeto.Delivery.API.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    //Busca produtos por restaurante
    //importante lembrar que ao buscar por chave estrageira, deve jogar isso no comando do springboot
    List<Produto> findAllByRestauranteIdId(Long restauranteId);

    //Busca produtos por Categoria
    List<Produto> findAllByCategoria(String categoira);

    //Busca por disponibilidade
    List<Produto> findAllByDisponivel(Boolean disponivel);
}
