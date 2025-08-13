package br.com.alura.orgs.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.orgs.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configEnterButton()
        configRegisterButton()
    }

    private fun configEnterButton() {
        val enterButton = binding.activityLoginButtonEnter
        enterButton.setOnClickListener {
            Log.i("TESTANDO", "configEnterButton: Clicando em Entrar")
        }
    }

    private fun configRegisterButton() {
        val registerButton = binding.activityLoginButtonRegister
        registerButton.setOnClickListener {
            Log.i("TESTANDO", "configRegisterButton: Cliando em Cadastrar Usuário")
        }
    }
}