package br.com.alura.orgs.extensions

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

fun BigDecimal.formaterCurrencyBrazil(): String {
    val numberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
    return numberFormat.format(this)
}
