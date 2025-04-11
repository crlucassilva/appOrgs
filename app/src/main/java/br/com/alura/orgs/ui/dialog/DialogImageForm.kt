package br.com.alura.orgs.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import br.com.alura.orgs.R
import br.com.alura.orgs.databinding.ImageFormBinding
import br.com.alura.orgs.extensions.loadImage
import coil3.load
import coil3.request.placeholder

class DialogImageForm(private val context: Context) {

    fun show(defaultUrl: String? = null, whenImageLoaded: (image: String) -> Unit) {
        ImageFormBinding.inflate(LayoutInflater.from(context)).apply {
            defaultUrl?.let {
                imageFormDefaultImage.loadImage(it)
                imageFormUrl.setText(it)
            }

            imageFormButtonUpload.setOnClickListener {
                val url = imageFormUrl.text.toString()
                imageFormDefaultImage.load(url) {
                    placeholder(R.drawable.placeholder)
                }
            }

            AlertDialog.Builder(context)
                .setView(root)
                .setPositiveButton("Confirmar") { _, _ ->
                    val url = imageFormUrl.text.toString()
                    whenImageLoaded(url)
                }
                .setNegativeButton("Cancelar") { _, _ ->
                }
                .show()
        }
    }
}