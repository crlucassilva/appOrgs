package br.com.alura.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.orgs.R
import br.com.alura.orgs.dao.ProductDao
import br.com.alura.orgs.ui.recycleview.adapter.ProductListAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ProductsListActivity : AppCompatActivity(R.layout.activity_products_list) {

    private val dao = ProductDao()
    private val adapter = ProductListAdapter(context = this, products = dao.findAll())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configRecycleView()
        configFab()
    }

    override fun onResume() {
        super.onResume()
        adapter.update(dao.findAll())
    }

    private fun configFab() {
        val fab = findViewById<FloatingActionButton>(R.id.activity_product_list_floatingActionButton)
        fab.setOnClickListener {
            goToFormProduct()
        }
    }

    private fun goToFormProduct() {
        val intent = Intent(this, FormProductActivity::class.java)
        startActivity(intent)
    }

    private fun configRecycleView() {
        val recycleView = findViewById<RecyclerView>(R.id.recycleView)
        recycleView.adapter = adapter
    }
}