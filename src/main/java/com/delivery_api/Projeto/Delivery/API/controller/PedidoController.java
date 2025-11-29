package com.delivery_api.Projeto.Delivery.API.controller;

import com.delivery_api.Projeto.Delivery.API.entity.Pedido;
import com.delivery_api.Projeto.Delivery.API.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    /**
     * Cadastrar novo pedido
     */
    @PostMapping
    public ResponseEntity<?> cadastrar(@Validated @RequestBody Pedido pedido) {
        try {
            Pedido pedidoSalvo = pedidoService.cadastrar(pedido);
            return ResponseEntity.status(HttpStatus.CREATED).body(pedidoSalvo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno no servidor");
        }
    }

    /**
     * Listar pedidos ativos
     */
    @GetMapping
    public ResponseEntity<List<Pedido>> listar() {
        List<Pedido> pedidos = pedidoService.listarAtivos();
        return ResponseEntity.ok(pedidos);
    }

    /**
     * Buscar pedido por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Optional<Pedido> pedido = pedidoService.buscarPorId(id);

        if (pedido.isPresent()) {
            return ResponseEntity.ok(pedido.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Atualizar pedido
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id,
                                       @Validated @RequestBody Pedido pedido) {
        try {
            Pedido pedidoAtualizado = pedidoService.atualizar(id, pedido);
            return ResponseEntity.ok(pedidoAtualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno no servidor");
        }
    }

    /**
     * Inativar pedido
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> inativar(@PathVariable Long id) {
        try {
            pedidoService.inativar(id);
            return ResponseEntity.ok("Pedido inativado com sucesso");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno no servidor");
        }
    }

    /**
     * Buscar pedidos por cliente
     */
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Pedido>> buscarPorCliente(@PathVariable Long clienteId) {
        List<Pedido> pedidos = pedidoService.buscarPorCliente(clienteId);
        return ResponseEntity.ok(pedidos);
    }

    /**
     * Buscar pedidos por status
     */
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Pedido>> buscarPorStatus(@PathVariable String status) {
        List<Pedido> pedidos = pedidoService.buscarPorStatus(status);
        return ResponseEntity.ok(pedidos);
    }
}
