package br.com.alura.orgs.ui.recycleview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.orgs.databinding.ProductItemBinding
import br.com.alura.orgs.extensions.formaterCurrencyBrazil
import br.com.alura.orgs.extensions.loadImage
import br.com.alura.orgs.model.Product

class ProductListAdapter(
    private val context: Context,
    products: List<Product>,
    var whenClickOnTheListener: (product: Product) -> Unit = {}
) : RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {

    private val products = products.toMutableList()

    inner class ViewHolder(private val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var product: Product

        init {
            binding.root.setOnClickListener {
                if (::product.isInitialized) {
                    whenClickOnTheListener(product)
                }
            }
        }

        fun vincula(product: Product) {
            this.product = product
            val name = binding.productItemName
            name.text = product.name
            val description = binding.productItemDescription
            description.text = product.description
            val value = binding.productItemValue
            val currencyValue = product.value.formaterCurrencyBrazil()
            value.text = currencyValue

            val visibility = if (product.image != null) {
                View.VISIBLE
            } else {
                View.GONE
            }

            binding.productItemImageView.visibility = visibility
            binding.productItemImageView.loadImage(product.image)
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