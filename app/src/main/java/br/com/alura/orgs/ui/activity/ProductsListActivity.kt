package br.com.alura.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.orgs.R
import br.com.alura.orgs.database.AppDatabase
import br.com.alura.orgs.databinding.ActivityProductsListBinding
import br.com.alura.orgs.model.Product
import br.com.alura.orgs.ui.recycleview.adapter.ProductListAdapter

class ProductsListActivity : AppCompatActivity() {

    private val adapter = ProductListAdapter(context = this)
    private val binding by lazy {
        ActivityProductsListBinding.inflate(layoutInflater)
    }

    private val productDao by lazy {
        AppDatabase.getInstance(this).productDao()
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
        adapter.update(productDao.findAll())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_product_list, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
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
            Log.i("Product Details", "onOptionsItemSelected: Remover")
        }
        adapter.whenClickOnTheEdit = {
            Log.i("Product Details", "onOptionsItemSelected: Editar")
        }
    }
}