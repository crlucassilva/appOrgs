package br.com.alura.orgs.ui.activity

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import br.com.alura.orgs.databinding.ActivityUserDetailsBinding
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class UserDetailsActivity : UserBaseActivity() {

    private val binding by lazy {
        ActivityUserDetailsBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configExitButton()
        fillInFields()
    }

    private fun fillInFields() {
        lifecycleScope.launch {
            user.filterNotNull().collect {
                loggedUser ->
                binding.activityUserDetailsUserName.text = loggedUser.id
                binding.activityUserDetailsName.text = loggedUser.name
            }
        }
    }

    private fun configExitButton() {
        binding.activityUserDetailsExitButton.setOnClickListener {
            showLogoutConfirmation()
        }
    }
}