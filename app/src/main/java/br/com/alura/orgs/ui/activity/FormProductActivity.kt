package br.com.alura.orgs.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import br.com.alura.orgs.dao.ProductDao
import br.com.alura.orgs.database.AppDatabase
import br.com.alura.orgs.databinding.ActivityFormProductBinding
import br.com.alura.orgs.extensions.loadImage
import br.com.alura.orgs.model.Product
import br.com.alura.orgs.ui.dialog.DialogImageForm
import java.math.BigDecimal

class FormProductActivity :
    AppCompatActivity() {

    private val binding by lazy {
        ActivityFormProductBinding.inflate(layoutInflater)
    }

    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configSaveButton()
        setContentView(binding.root)
        title = "Cadastrar Produto"
        configDialog()
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
        val db = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "orgs.db"
        ).allowMainThreadQueries()
            .build()
        val productDao = db.productDao()

        saveButton.setOnClickListener {
            val newProduct = createProduct()
            productDao.save(
                newProduct
            )
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
            value = value,
            image = url
        )
    }
}