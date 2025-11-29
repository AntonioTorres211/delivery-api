package com.delivery_api.Projeto.Delivery.API.service;

import com.delivery_api.Projeto.Delivery.API.entity.Produto;
import com.delivery_api.Projeto.Delivery.API.repository.ProdutoRepository;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    /**
     * Cadastrar novo produto
     */
    public Produto cadastrar(Produto produto) {

        validarDadosProduto(produto);

        // Produto começa ativo por padrão
        produto.setAtivo(true);

        return produtoRepository.save(produto);
    }

    /**
     * Buscar produto por ID
     */
    @Transactional(readOnly = true)
    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    /**
     * Listar produtos ativos
     */
    @Transactional(readOnly = true)
    public List<Produto> listarAtivos() {
        return produtoRepository.findByAtivoTrue();
    }

    /**
     * Atualizar produto
     */
    public Produto atualizar(Long id, Produto produtoAtualizado) {
        Produto produto = buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado: " + id));

        validarDadosProduto(produtoAtualizado);

        produto.setNome(produtoAtualizado.getNome());
        produto.setDescricao(produtoAtualizado.getDescricao());
        produto.setPreco(produtoAtualizado.getPreco());
        produto.setCategoria(produtoAtualizado.getCategoria());
        produto.setImagemUrl(produtoAtualizado.getImagemUrl());

        return produtoRepository.save(produto);
    }

    /**
     * Inativar produto (soft delete)
     */
    public void inativar(Long id) {
        Produto produto = buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado: " + id));

        produto.inativar();
        produtoRepository.save(produto);
    }

    /**
     * Buscar produto por nome (nome parcial)
     */
    @Transactional(readOnly = true)
    public List<Produto> buscarPorNome(String nome) {
        return produtoRepository.findByNomeContainingIgnoreCase(nome);
    }

    /**
     * Buscar por categoria
     */
    @Transactional(readOnly = true)
    public List<Produto> buscarPorCategoria(String categoria) {
        return produtoRepository.findByCategoriaIgnoreCase(categoria);
    }

    /**
     * Buscar produtos por faixa de preço
     */
    @Transactional(readOnly = true)
    public List<Produto> buscarPorFaixaDePreco(BigDecimal min, BigDecimal max) {
        return produtoRepository.findByPrecoBetween(min, max);
    }

    /**
     * Validações de negócio
     */
    private void validarDadosProduto(Produto produto) {

        if (produto.getNome() == null || produto.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do produto é obrigatório");
        }

        if (produto.getPreco() == null || produto.getPreco().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Preço do produto deve ser maior que zero");
        }

        if (produto.getCategoria() == null || produto.getCategoria().trim().isEmpty()) {
            throw new IllegalArgumentException("Categoria do produto é obrigatória");
        }
    }
}
