package br.com.alura.orgs.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.orgs.R
import br.com.alura.orgs.dao.ProductDao
import br.com.alura.orgs.model.Product
import java.math.BigDecimal

class FormProductActivity :
    AppCompatActivity(R.layout.activity_form_product) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configSaveButton()
    }

    private fun configSaveButton() {
        val saveButton = findViewById<Button>(R.id.activity_form_product_save_button)
        val dao = ProductDao()
        saveButton.setOnClickListener {
            val productNovo = createProduct()
            dao.add(productNovo)
            Log.i("FormProductActivity", "onCreate: ${dao.findAll()}")
            finish()
        }
    }

    private fun createProduct(): Product {
        val fieldName = findViewById<EditText>(R.id.activity_form_product_name)
        val name = fieldName.text.toString()
        val fielddescription = findViewById<EditText>(R.id.activity_form_product_description)
        val description = fielddescription.text.toString()
        val fieldValue = findViewById<EditText>(R.id.activity_form_product_value)
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