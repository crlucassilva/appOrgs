package br.com.alura.orgs.dao

import br.com.alura.orgs.model.Product
import java.math.BigDecimal

class ProductDao {

    companion object {
        private val products = mutableListOf<Product>(
            Product(
                name = "Cesta de Frutas",
                description = "Frutas de época",
                value = BigDecimal("19.83")
            )
        )
    }

    fun add(product: Product) {
        products.add(product)
    }

    fun findAll() : List<Product> {
        return products.toList()
    }
}