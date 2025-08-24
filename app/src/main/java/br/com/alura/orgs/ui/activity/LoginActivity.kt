package br.com.alura.orgs.ui.activity

import android.content.Intent
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
            val user = binding.activityLoginUser.text.toString()
            val password = binding.activityLoginPassword.text.toString()
            Log.i("LoginActivity", "onCreate: $user - $password")
            val intent = Intent(this, ProductsListActivity::class.java)
            startActivity(intent)
        }
    }

    private fun configRegisterButton() {
        val registerButton = binding.activityLoginButtonRegister
        registerButton.setOnClickListener {
            val intent = Intent(this, FormRegisterActivity::class.java)
            startActivity(intent)
        }
    }
}