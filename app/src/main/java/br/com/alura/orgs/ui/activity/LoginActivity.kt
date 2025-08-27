package br.com.alura.orgs.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import br.com.alura.orgs.database.AppDatabase
import br.com.alura.orgs.databinding.ActivityLoginBinding
import br.com.alura.orgs.extensions.goTo
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    private val userDao by lazy {
        AppDatabase.getInstance(this).userDao()
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
            lifecycleScope.launch {
                userDao.authenticate(user, password)?.let { user ->
                    goTo(ProductsListActivity::class.java) {
                        putExtra("KEY_USER_ID", user.id)
                    }
                } ?: Toast.makeText(
                    this@LoginActivity,
                    "Falha na autenticação",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun configRegisterButton() {
        val registerButton = binding.activityLoginButtonRegister
        registerButton.setOnClickListener {
            goTo(FormRegisterActivity::class.java)
        }
    }
}