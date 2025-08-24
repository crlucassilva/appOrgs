package br.com.alura.orgs.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.orgs.databinding.ActivityFormRegisterBinding

class FormRegisterActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityFormRegisterBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "Cadastrar Usuário"
        configRegisterButton()
    }

    private fun configRegisterButton() {
        val saveButton = binding.activityFormRegisterButton

        saveButton.setOnClickListener {
            Log.i("Register Button", "configRegisterButton: Register")
        }
    }
}