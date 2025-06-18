package br.com.alura.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.orgs.database.AppDatabase
import br.com.alura.orgs.databinding.ActivityProductsListBinding
import br.com.alura.orgs.ui.recycleview.adapter.ProductListAdapter

class ProductsListActivity : AppCompatActivity() {

    private val adapter = ProductListAdapter(context = this)
    private val binding by lazy {
        ActivityProductsListBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configRecycleView()
        configFab()
        binding.actvityProductsListSwipeReflesh.setOnRefreshListener {
            Log.i("TAG", "onCreate: Atualizando")
            binding.actvityProductsListSwipeReflesh.isRefreshing = false
        }

    }

    override fun onResume() {
        super.onResume()
        val db = AppDatabase.getInstance(this)
        val productDao = db.productDao()
        adapter.update(productDao.findAll())
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
                putExtra(CHAVE_PRODUTO, it)
            }
            startActivity(intent)
        }
        adapter.whenClickOnTheDelete = {
            Log.i("Product Details", "onOptionsItemSelected: Remover")
        }
        adapter.whenClickOnTheEdit = {
            Log.i("Product Details", "onOptionsItemSelected: Editar")
        }
    }

}