package br.com.alura.orgs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import br.com.alura.orgs.database.AppDatabase
import br.com.alura.orgs.database.dao.ProductDao
import br.com.alura.orgs.databinding.ActivityFormProductBinding
import br.com.alura.orgs.extensions.loadImage
import br.com.alura.orgs.model.Product
import br.com.alura.orgs.ui.dialog.DialogImageForm
import kotlinx.coroutines.launch
import java.math.BigDecimal

class FormProductActivity :
    AppCompatActivity() {

    private val binding by lazy {
        ActivityFormProductBinding.inflate(layoutInflater)
    }
    private var url: String? = null
    private var productId = 0L
    private val productDao: ProductDao by lazy {
        AppDatabase.getInstance(this).productDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configSaveButton()
        setContentView(binding.root)
        title = "Cadastrar Produto"
        configDialog()
        tryLoadProduct()
    }

    private fun tryLoadProduct() {
        productId = intent.getLongExtra(CHAVE_PRODUTO_ID, 0L)
    }

    override fun onResume() {
        super.onResume()
        findProduct()
    }

    private fun findProduct() {
        lifecycleScope.launch {
            productDao.findById(productId).collect { product ->
                product?.let {
                    title = "Alterar Produto"
                    fillInFields(it)
                }
            }
        }
    }

    private fun fillInFields(product: Product) {
        url = product.image
        binding.activityFormProductImageView.loadImage(product.image)
        binding.activityFormProductName.setText(product.name)
        binding.activityFormProductDescription.setText(product.description)
        binding.activityFormProductValue.setText(product.value.toPlainString())
    }

    private fun configDialog() {
        binding.activityFormProductImageView.setOnClickListener {
            DialogImageForm(this).show(url) { image ->
                url = image
                binding.activityFormProductImageView.loadImage(url)
            }
        }
    }

    private fun configSaveButton() {
        val saveButton = binding.activityFormProductSaveButton
        saveButton.setOnClickListener {
            val newProduct = createProduct()
            lifecycleScope.launch {
                productDao.save(newProduct)
                finish()
            }
        }
    }

    private fun createProduct(): Product {
        val fieldName = binding.activityFormProductName
        val name = fieldName.text.toString()
        val fieldDescription = binding.activityFormProductDescription
        val description = fieldDescription.text.toString()
        val fieldValue = binding.activityFormProductValue
        val valueInText = fieldValue.text.toString()
        val value = if (valueInText.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(valueInText)
        }

        return Product(
            id = productId,
            name = name,
            description = description,
            value = value,
            image = url
        )
    }
}