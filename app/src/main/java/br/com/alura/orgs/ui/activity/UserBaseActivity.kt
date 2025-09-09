package br.com.alura.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.lifecycleScope
import br.com.alura.orgs.database.AppDatabase
import br.com.alura.orgs.extensions.goTo
import br.com.alura.orgs.model.User
import br.com.alura.orgs.preferences.dataStore
import br.com.alura.orgs.preferences.loggedUserPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

abstract class UserBaseActivity : AppCompatActivity() {

    private val userDao by lazy {
        AppDatabase.getInstance(this).userDao()
    }
    private var _user: MutableStateFlow<User?> = MutableStateFlow(null)
    protected var user: StateFlow<User?> = _user

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            checkLoggedUser()
        }
    }

    private suspend fun checkLoggedUser() {
        dataStore.data.collect { preferences ->
            preferences[loggedUserPreferences]?.let { userId ->
                findUser(userId)
            } ?: goToLogin()
        }
    }

    private suspend fun findUser(userId: String) {
        _user.value = userDao
            .findId(userId)
            .firstOrNull()
    }

    protected suspend fun logoutUser() {
        dataStore.edit { it.remove(loggedUserPreferences) }
        finish()
    }

    private fun goToLogin() {
        goTo(LoginActivity::class.java) {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }
        finish()
    }
}