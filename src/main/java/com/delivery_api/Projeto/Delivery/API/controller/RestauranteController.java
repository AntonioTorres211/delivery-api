package com.delivery_api.Projeto.Delivery.API.controller;
import com.delivery_api.Projeto.Delivery.API.entity.Restaurante;
import com.delivery_api.Projeto.Delivery.API.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restaurantes")
@CrossOrigin(origins = "*")
public class RestauranteController {
    @Autowired
    private RestauranteService restauranteService;

    //SALVA O RESTAURANTE
    @PostMapping
    public ResponseEntity<?> cadastrar(@Validated @RequestBody Restaurante restaurante){
        try {
            Restaurante restauranteSalvo = restauranteService.cadastrar(restaurante);
            return ResponseEntity.status(HttpStatus.CREATED).body(restauranteSalvo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno do servidor");
        }
    }

    //LISTA DOS RESTAURANTES ATIVOS
    @GetMapping
    public ResponseEntity<?> listarTodos() {
        try {
            return ResponseEntity.ok(restauranteService.listarAtivos());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno do servidor");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(/*@PathVariable serve para valoress na url*/
            @PathVariable long id) {
        try {
            Optional<Restaurante> restaurante = restauranteService.buscarPorId(id);
            if (restaurante != null) {
                return ResponseEntity.ok(restaurante);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Restaurante não encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interdo do servidor");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable long id){
        try {
            restauranteService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno do servidor");
        }
    }
}
