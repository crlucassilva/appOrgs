package br.com.alura.orgs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.lifecycleScope
import br.com.alura.orgs.database.AppDatabase
import br.com.alura.orgs.databinding.ActivityLoginBinding
import br.com.alura.orgs.extensions.goTo
import br.com.alura.orgs.extensions.toHash
import br.com.alura.orgs.extensions.toast
import br.com.alura.orgs.preferences.dataStore
import br.com.alura.orgs.preferences.loggedUserPreferences
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
            val password = binding.activityLoginPassword.text.toString().toHash()
            authentic(user, password)
        }
    }

    private fun authentic(user: String, password: String) {
        lifecycleScope.launch {
            userDao.authenticate(user, password)?.let { user ->
                dataStore.edit { preferences ->
                    preferences[loggedUserPreferences] = user.id
                }
                goTo(ProductsListActivity::class.java)
                finish()
            } ?: toast("Falha na autenticação")
        }
    }

    private fun configRegisterButton() {
        val registerButton = binding.activityLoginButtonRegister
        registerButton.setOnClickListener {
            goTo(FormRegisterActivity::class.java)
        }
    }
}