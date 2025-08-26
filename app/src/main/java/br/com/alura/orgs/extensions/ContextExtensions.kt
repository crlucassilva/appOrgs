package br.com.alura.orgs.extensions

import android.content.Context
import android.content.Intent

fun Context.goTo(clazz: Class<*>) {
    Intent(this, clazz).apply {
        startActivity(this)
    }
}