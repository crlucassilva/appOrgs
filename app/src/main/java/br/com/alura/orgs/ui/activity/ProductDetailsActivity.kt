package br.com.alura.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.orgs.R
import br.com.alura.orgs.database.AppDatabase
import br.com.alura.orgs.databinding.ActivityProductDetailsBinding
import br.com.alura.orgs.extensions.formaterCurrencyBrazil
import br.com.alura.orgs.extensions.loadImage
import br.com.alura.orgs.model.Product

class ProductDetailsActivity() : AppCompatActivity() {

    private var productId: Long = 0L
    private var product: Product? = null
    private val binding by lazy {
        ActivityProductDetailsBinding.inflate(layoutInflater)
    }

    private val productDao by lazy {
        AppDatabase.getInstance(this).productDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        tryLoadProduct()
    }

    override fun onResume() {
        super.onResume()
        findProduct()
    }

    private fun findProduct() {
        product = productDao.findById(productId)
        product?.let {
            fillInFields(it)
        } ?: finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_product_details, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_product_details_remove -> {
                product?.let { productDao.remove(it) }
                finish()
            }

            R.id.menu_product_details_edit -> {
                Intent(this, FormProductActivity::class.java).apply {
                    putExtra(CHAVE_PRODUTO_ID, productId)
                    startActivity(this)
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun tryLoadProduct() {
        productId = intent.getLongExtra(CHAVE_PRODUTO_ID, 0L)
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