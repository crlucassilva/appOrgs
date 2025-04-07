package br.com.alura.orgs.dao

import br.com.alura.orgs.model.Produto

class ProductDao {

    companion object {
        private val produtos = mutableListOf<Produto>()
    }

    fun add(produto: Produto) {
        produtos.add(produto)
    }

    fun findAll() : List<Produto> {
        return produtos.toList()
    }
}