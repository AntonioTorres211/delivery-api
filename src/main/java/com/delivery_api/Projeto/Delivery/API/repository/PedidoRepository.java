package com.delivery_api.Projeto.Delivery.API.repository;
//Buscar pedidos por cliente, filtrar por status/data, relatórios

import com.delivery_api.Projeto.Delivery.API.entity.Pedido;
import com.delivery_api.Projeto.Delivery.API.entity.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    //Busca lista de pedidos por cliente
    List<Pedido> findAllByClienteId(Long clienteId);

    //Busca lista de pedidos por cliente e status
    List<Pedido> findAllByClienteIdAndStatus(Long clienteId, String status);

    //Busca lista dos pedidos por status e hora
    List<Pedido> findAllByStatusAndDataPedido(String status, LocalDateTime dataPedido);

    //Buca lista dos pedidos por status
    List<Pedido> findAllByStatus(String status);

    //Numero do pedido e devolve relatorio
    Optional<Pedido> findByNumeroPedido(String numeroPedido);

}
