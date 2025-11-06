package com.delivery_api.Projeto.Delivery.API.service;
import com.delivery_api.Projeto.Delivery.API.entity.Pedido;
import com.delivery_api.Projeto.Delivery.API.entity.Restaurante;
import com.delivery_api.Projeto.Delivery.API.repository.PedidoRepository;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    /*
    @Column(name = "numero_pedido")
    private String numeroPedido;

    @Column(name = "data_pedido")
    private LocalDateTime dataPedido;

    @Column(name = "cliente_id")
    private Long clienteId;

    @ManyToOne
    @JoinColumn(name = "restaurante_id")
    private Restaurante restaurante;


     */



}
