package com.delivery_api.Projeto.Delivery.API.controller;

import com.delivery_api.Projeto.Delivery.API.entity.Produto;
import com.delivery_api.Projeto.Delivery.API.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    /**
     * Cadastrar produto
     */
    @PostMapping
    public ResponseEntity<?> cadastrar(@Validated @RequestBody Produto produto) {
        try {
            Produto salvo = produtoService.cadastrar(produto);
            return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno no servidor");
        }
    }

    /**
     * Listar produtos ativos
     */
    @GetMapping
    public ResponseEntity<List<Produto>> listar() {
        List<Produto> produtos = produtoService.listarAtivos();
        return ResponseEntity.ok(produtos);
    }

    /**
     * Buscar por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Optional<Produto> produto = produtoService.buscarPorId(id);

        if (produto.isPresent()) {
            return ResponseEntity.ok(produto.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Atualizar produto
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id,
                                       @Validated @RequestBody Produto produto) {
        try {
            Produto atualizado = produtoService.atualizar(id, produto);
            return ResponseEntity.ok(atualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno no servidor");
        }
    }

    /**
     * Inativar produto
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> inativar(@PathVariable Long id) {
        try {
            produtoService.inativar(id);
            return ResponseEntity.ok("Produto inativado com sucesso");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno no servidor");
        }
    }

    /**
     * Buscar por nome
     */
    @GetMapping("/buscar")
    public ResponseEntity<List<Produto>> buscarPorNome(@RequestParam String nome) {
        List<Produto> produtos = produtoService.buscarPorNome(nome);
        return ResponseEntity.ok(produtos);
    }

    /**
     * Buscar por categoria
     */
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Produto>> buscarPorCategoria(@PathVariable String categoria) {
        List<Produto> produtos = produtoService.buscarPorCategoria(categoria);
        return ResponseEntity.ok(produtos);
    }

    /**
     * Buscar por faixa de preço
     */
    @GetMapping("/preco")
    public ResponseEntity<List<Produto>> buscarPorFaixa(
            @RequestParam BigDecimal min,
            @RequestParam BigDecimal max) {

        List<Produto> produtos = produtoService.buscarPorFaixaDePreco(min, max);
        return ResponseEntity.ok(produtos);
    }
}
