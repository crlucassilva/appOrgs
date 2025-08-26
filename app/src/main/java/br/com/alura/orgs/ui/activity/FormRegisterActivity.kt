package br.com.alura.orgs.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.orgs.databinding.ActivityFormRegisterBinding
import br.com.alura.orgs.model.User

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
            val newUser = createUser()
            Log.i("CadastrarUsuario", "onCreate: $newUser")
            finish()
        }
    }

    private fun createUser(): User {
        val user = binding.activityFormRegisterUser.text.toString()
        val name = binding.activityFormRegisterName.text.toString()
        val password = binding.activityFormRegisterPassword.text.toString()
        return User(user ,name, password)
    }
}