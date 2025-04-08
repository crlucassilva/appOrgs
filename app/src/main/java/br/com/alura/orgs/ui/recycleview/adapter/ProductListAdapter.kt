package br.com.alura.orgs.ui.recycleview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.orgs.databinding.ProductItemBinding
import br.com.alura.orgs.model.Product

class ProductListAdapter(
    private val context: Context,
    products: List<Product>
) : RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {

    private val products = products.toMutableList()

    class ViewHolder(binding: ProductItemBinding): RecyclerView.ViewHolder(binding.root) {

        private val name = binding.productItemName
        private val description = binding.productItemDescription
        private val value = binding.productItemValue

        fun vincula(product: Product) {
            name.text = product.name
            description.text = product.description
            value.text = product.value.toPlainString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ProductItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.vincula(product)
    }

    fun update(products: List<Product>) {
        this.products.clear()
        this.products.addAll(products)
        notifyDataSetChanged()
    }
}