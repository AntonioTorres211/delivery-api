package com.delivery_api.Projeto.Delivery.API.service;

import com.delivery_api.Projeto.Delivery.API.entity.Pedido;
import com.delivery_api.Projeto.Delivery.API.repository.PedidoRepository;
import com.delivery_api.Projeto.Delivery.API.repository.ClienteRepository;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    /**
     * Cadastrar novo pedido
     */
    public Pedido cadastrar(Pedido pedido) {

        validarDadosPedido(pedido);

        // Definir pedido como ativo por padrão
        pedido.setAtivo(true);

        return pedidoRepository.save(pedido);
    }

    /**
     * Buscar pedido por ID
     */
    @Transactional(readOnly = true)
    public Optional<Pedido> buscarPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    /**
     * Listar todos os pedidos ativos
     */
    @Transactional(readOnly = true)
    public List<Pedido> listarAtivos() {
        return pedidoRepository.findByAtivoTrue();
    }

    /**
     * Atualizar pedido
     */
    public Pedido atualizar(Long id, Pedido pedidoAtualizado) {
        Pedido pedido = buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado: " + id));

        validarDadosPedido(pedidoAtualizado);

        // Atualizar campos
        pedido.setCliente(pedidoAtualizado.getCliente());
        pedido.setItens(pedidoAtualizado.getItens());
        pedido.setValorTotal(pedidoAtualizado.getValorTotal());
        pedido.setStatus(pedidoAtualizado.getStatus());
        pedido.setEnderecoEntrega(pedidoAtualizado.getEnderecoEntrega());

        return pedidoRepository.save(pedido);
    }

    /**
     * Inativar pedido (soft delete)
     */
    public void inativar(Long id) {
        Pedido pedido = buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado: " + id));

        pedido.inativar();
        pedidoRepository.save(pedido);
    }

    /**
     * Buscar por status
     */
    @Transactional(readOnly = true)
    public List<Pedido> buscarPorStatus(String status) {
        return pedidoRepository.findByStatusIgnoreCase(status);
    }

    /**
     * Buscar pedidos de um cliente
     */
    @Transactional(readOnly = true)
    public List<Pedido> buscarPorCliente(Long clienteId) {

        if (!clienteRepository.existsById(clienteId)) {
            throw new IllegalArgumentException("Cliente não encontrado: " + clienteId);
        }

        return pedidoRepository.findByClienteId(clienteId);
    }

    /**
     * Validações do pedido
     */
    private void validarDadosPedido(Pedido pedido) {

        // Cliente obrigatório
        if (pedido.getCliente() == null || pedido.getCliente().getId() == null) {
            throw new IllegalArgumentException("Cliente é obrigatório para criar um pedido");
        }

        // Verificar se o cliente existe
        if (!clienteRepository.existsById(pedido.getCliente().getId())) {
            throw new IllegalArgumentException("Cliente não encontrado: " + pedido.getCliente().getId());
        }

        // Pedido precisa ter itens
        if (pedido.getItens() == null || pedido.getItens().isEmpty()) {
            throw new IllegalArgumentException("O pedido deve conter pelo menos um item");
        }

        // Valor total
        if (pedido.getValorTotal() == null || pedido.getValorTotal().doubleValue() <= 0) {
            throw new IllegalArgumentException("Valor total inválido");
        }

        // Status obrigatório
        if (pedido.getStatus() == null || pedido.getStatus().trim().isEmpty()) {
            throw new IllegalArgumentException("Status do pedido é obrigatório");
        }
    }
}
