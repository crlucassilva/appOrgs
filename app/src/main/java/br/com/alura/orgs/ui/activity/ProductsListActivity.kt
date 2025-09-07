package br.com.alura.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.lifecycleScope
import br.com.alura.orgs.R
import br.com.alura.orgs.database.AppDatabase
import br.com.alura.orgs.databinding.ActivityProductsListBinding
import br.com.alura.orgs.extensions.goTo
import br.com.alura.orgs.model.Product
import br.com.alura.orgs.preferences.dataStore
import br.com.alura.orgs.preferences.loggedUserPreferences
import br.com.alura.orgs.ui.recycleview.adapter.ProductListAdapter
import kotlinx.coroutines.launch

class ProductsListActivity : AppCompatActivity() {

    private val adapter = ProductListAdapter(context = this)
    private val binding by lazy {
        ActivityProductsListBinding.inflate(layoutInflater)
    }
    private val productDao by lazy {
        AppDatabase.getInstance(this).productDao()
    }

    private val userDao by lazy {
        AppDatabase.getInstance(this).userDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configRecycleView()
        configFab()
        binding.actvityProductsListSwipeReflesh.setOnRefreshListener {
            updateList()
            binding.actvityProductsListSwipeReflesh.isRefreshing = false
        }
        updateList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_product_list, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        lifecycleScope.launch {
            val orderedProducts: List<Product>? = when (item.itemId) {
                R.id.menu_product_list_name_desc ->
                    productDao.findAllOrderNameDesc()

                R.id.menu_product_list_name_asc ->
                    productDao.findAllOrderNameAsc()

                R.id.menu_product_list_description_desc ->
                    productDao.findAllOrderDescriptionDesc()

                R.id.menu_product_list_description_asc ->
                    productDao.findAllOrderDescriptionAsc()

                R.id.menu_product_list_value_desc ->
                    productDao.findAllOrderValueDesc()

                R.id.menu_product_list_value_asc ->
                    productDao.findAllOrderValueAsc()

                else -> null
            }
            orderedProducts?.let {
                adapter.update(it)
            }

            when(item.itemId) {
                R.id.menu_list_products_exit -> {
                    dataStore.edit { preferences ->
                        preferences.remove(loggedUserPreferences)
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun configFab() {
        val fab = binding.activityProductListFloatingActionButton
        fab.setOnClickListener {
            goToFormProduct()
        }
    }

    private fun goToFormProduct() {
        val intent = Intent(this, FormProductActivity::class.java)
        startActivity(intent)
    }

    private fun configRecycleView() {
        val recycleView = binding.activityProductsListRecycleView
        recycleView.adapter = adapter
        adapter.whenClickOnTheListener = {
            val intent = Intent(
                this,
                ProductDetailsActivity::class.java
            ).apply {
                putExtra(CHAVE_PRODUTO_ID, it.id)
            }
            startActivity(intent)
        }
        adapter.whenClickOnTheDelete = {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder
                .setMessage("Você tem certeza que quer remover esse item?")
                .setTitle("Excluir item?")
                .setPositiveButton("Sim") { dialog, which ->
                    lifecycleScope.launch {
                        productDao.remove(it)
                        updateList()
                    }
                }
                .setNegativeButton("Não") { dialog, which ->
                }
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
        adapter.whenClickOnTheEdit = {
            Intent(this, FormProductActivity::class.java).apply {
                putExtra(CHAVE_PRODUTO_ID, it.id)
                startActivity(this)
            }
        }
    }

    private fun updateList() {
        lifecycleScope.launch {
            launch {
                productDao.findAll().collect { products ->
                    adapter.update(products)
                }
            }

            launch {
                dataStore.data.collect { preferences ->
                    preferences[loggedUserPreferences]?.let { userId ->
                        launch {
                            userDao.findId(userId).collect {
                                Log.i("ListaProdutos", "onCreate: $it")
                            }
                        }
                    } ?: goToLogin()
                }
            }
        }
    }

    private fun goToLogin() {
        goTo(LoginActivity::class.java)
        finish()
    }
}
