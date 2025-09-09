package br.com.alura.orgs.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import br.com.alura.orgs.database.AppDatabase
import br.com.alura.orgs.databinding.ActivityFormRegisterBinding
import br.com.alura.orgs.extensions.toHash
import br.com.alura.orgs.extensions.toast
import br.com.alura.orgs.model.User
import kotlinx.coroutines.launch

class FormRegisterActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityFormRegisterBinding.inflate(layoutInflater)
    }

    private val dao by lazy {
        AppDatabase.getInstance(this).userDao()
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
            register(newUser)
        }
    }

    private fun register(user: User) {
        lifecycleScope.launch {
            try {
                dao.save(user)
                finish()
            } catch (e: Exception) {
                Log.e("CadastroUsuario", "configureButtonRegister", e)
                toast("Falha ao cadastrar usuário")
            }
        }
    }

    private fun createUser(): User {
        val user = binding.activityFormRegisterUser.text.toString()
        val name = binding.activityFormRegisterName.text.toString()
        val password = binding.activityFormRegisterPassword.text.toString().toHash()
        return User(user ,name, password)
    }
}