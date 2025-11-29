package com.delivery_api.Projeto.Delivery.API.repository;

import com.delivery_api.Projeto.Delivery.API.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    // ---- EXISTENTES ----

    //Busca lista de pedidos por cliente
    List<Pedido> findAllByClienteId(Long clienteId);

    //Busca lista de pedidos por cliente e status
    List<Pedido> findAllByClienteIdAndStatus(Long clienteId, String status);

    //Busca lista dos pedidos por status e data
    List<Pedido> findAllByStatusAndDataPedido(String status, LocalDateTime dataPedido);

    //Busca lista dos pedidos por status
    List<Pedido> findAllByStatus(String status);

    //Número do pedido e devolve relatório
    Optional<Pedido> findByNumeroPedido(String numeroPedido);


    // ---- ADICIONADOS PELO PedidoService ----

    // Lista pedidos ativos (soft delete)
    List<Pedido> findByAtivoTrue();

    // Status ignorando maiúsculas/minúsculas
    List<Pedido> findByStatusIgnoreCase(String status);

    // Buscar pedidos usando o nome mais simples usado no service
    List<Pedido> findByClienteId(Long clienteId);
}
