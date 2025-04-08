package br.com.alura.orgs.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.orgs.dao.ProductDao
import br.com.alura.orgs.databinding.ActivityFormProductBinding
import br.com.alura.orgs.model.Product
import java.math.BigDecimal

class FormProductActivity :
    AppCompatActivity() {

        private val binding by lazy {
            ActivityFormProductBinding.inflate(layoutInflater)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configSaveButton()
        setContentView(binding.root)
    }

    private fun configSaveButton() {
        val saveButton = binding.activityFormProductSaveButton
        val dao = ProductDao()
        saveButton.setOnClickListener {
            val productNovo = createProduct()
            dao.add(productNovo)
            Log.i("FormProductActivity", "onCreate: ${dao.findAll()}")
            finish()
        }
    }

    private fun createProduct(): Product {
        val fieldName = binding.activityFormProductName
        val name = fieldName.text.toString()
        val fielddescription = binding.activityFormProductDescription
        val description = fielddescription.text.toString()
        val fieldValue = binding.activityFormProductValue
        val valueInText = fieldValue.text.toString()
        val value = if (valueInText.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(valueInText)
        }

        return Product(
            name = name,
            description = description,
            value = value
        )
    }
}