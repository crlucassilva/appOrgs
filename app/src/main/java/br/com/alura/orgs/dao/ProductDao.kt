package br.com.alura.orgs.dao

import br.com.alura.orgs.model.Product

class ProductDao {

    companion object {
        private val products = mutableListOf<Product>()
    }

    fun add(product: Product) {
        products.add(product)
    }

    fun findAll() : List<Product> {
        return products.toList()
    }
}