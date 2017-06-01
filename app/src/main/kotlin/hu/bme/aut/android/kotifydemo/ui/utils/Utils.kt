package hu.bme.aut.android.kotifydemo.ui.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import hu.bme.aut.android.kotifydemo.InjectorDelegate.context
import retrofit2.Retrofit

inline fun <reified T : Any> Retrofit.create(): T {
    return this.create(T::class.java)
}

inline fun <reified T : Any> Intent(context: Context): android.content.Intent {
    return android.content.Intent(context, T::class.java)
}

val Context.inflater: LayoutInflater by lazy {
    LayoutInflater.from(context)
}

fun Context.showShortToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.showLongToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}
