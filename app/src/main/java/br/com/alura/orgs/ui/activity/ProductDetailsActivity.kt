package br.com.alura.orgs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.orgs.R
import br.com.alura.orgs.databinding.ActivityProductDetailsBinding
import br.com.alura.orgs.extensions.formaterCurrencyBrazil
import br.com.alura.orgs.extensions.loadImage
import br.com.alura.orgs.model.Product

class ProductDetailsActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityProductDetailsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        tryLoadProduct()
    }

    private fun tryLoadProduct() {
        intent.getParcelableExtra<Product>(CHAVE_PRODUTO)?.let { loadedProduct ->
            fillInFields(loadedProduct)
        } ?: finish()
    }
    private fun fillInFields(product: Product) {
        with(binding) {
            activityProductDetailImageView.loadImage(product.image)
            activityProductDetailsTitle.text = product.name
            activityProductDetailDescription.text = product.description
            activityProductDetailValue.text = product.value.formaterCurrencyBrazil()
        }
    }
}

