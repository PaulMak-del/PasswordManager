package com.example.passwordmanager.fragments/*
package com.example.passwordmanager

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory (
    private val app: App
    ) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = when (modelClass) {
            PasswordsListViewModel::class.java -> {
                PasswordsListViewModel(app.passwordService)
            }
            else -> {
                throw java.lang.IllegalStateException("Unknown view model class")
            }
        }
        return viewModel as T
    }
}

fun Fragment.factory() = ViewModelFactory(requireContext().applicationContext as App)

 */