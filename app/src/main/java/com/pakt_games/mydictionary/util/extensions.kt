package com.pakt_games.mydictionary.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

inline fun<reified T : AppCompatActivity> Context.startActivity(block : Intent.() -> Unit = {}){
    val intent  = Intent(this , T::class.java)
    startActivity(
        intent.also {
            block.invoke(it)
        }
    )
}
fun Fragment.showToast(messageToShow : String){
    Toast.makeText(requireContext(), messageToShow, Toast.LENGTH_LONG).show()
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}
fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}
