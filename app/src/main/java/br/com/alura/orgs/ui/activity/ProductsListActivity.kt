package br.com.alura.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.orgs.dao.ProductDao
import br.com.alura.orgs.databinding.ActivityProductsListBinding
import br.com.alura.orgs.ui.recycleview.adapter.ProductListAdapter

class ProductsListActivity : AppCompatActivity() {

    private val dao = ProductDao()
    private val adapter = ProductListAdapter(context = this, products = dao.findAll())
    private val binding by lazy {
        ActivityProductsListBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configRecycleView()
        configFab()
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        adapter.update(dao.findAll())
    }

    private fun configFab() {
        binding.activityProductListFloatingActionButton.setOnClickListener {
            goToFormProduct()
        }
    }

    private fun goToFormProduct() {
        val intent = Intent(this, FormProductActivity::class.java)
        startActivity(intent)
    }

    private fun configRecycleView() {
        val recycleView = binding.recycleView
        recycleView.adapter = adapter
    }
}