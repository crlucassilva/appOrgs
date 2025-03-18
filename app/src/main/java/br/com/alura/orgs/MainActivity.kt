package br.com.alura.orgs

import android.app.Activity
import android.os.Bundle
import android.widget.TextView

class MainActivity: Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val nome = findViewById<TextView>(R.id.nome)
        nome.text = "Cesta de Frutas"
        val descricao = findViewById<TextView>(R.id.descricao)
        descricao.text = "Laranja, manga e maçã"
        val valor = findViewById<TextView>(R.id.valor)
        valor.text = "19.99"
    }
}